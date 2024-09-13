package io.holixon.axon.eclipsestore.root

import io.holixon.axon.eclipsestore.root.FileSystemHelper.isStorageExists
import mu.KLogging
import org.axonframework.queryhandling.NoHandlerForQueryException
import org.axonframework.queryhandling.QueryBus
import org.eclipse.store.integrations.spring.boot.types.factories.EmbeddedStorageFoundationFactory
import org.eclipse.store.integrations.spring.boot.types.factories.EmbeddedStorageManagerFactory
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager
import java.util.concurrent.ConcurrentHashMap


/**
 * Default storage root using a map of named elements.
 */
@Suppress("UNCHECKED_CAST")
class StorageRoot {

  @Transient
  private lateinit var storageManager: EmbeddedStorageManager

  private val elements: ConcurrentHashMap<String, Any> = ConcurrentHashMap()

  companion object : KLogging() {
    /**
     * Initialize storage root for / from given storage manager.
     * @param managerFactory storage manager factory.
     * @param foundationFactory foundation factory.
     * @param storeProjectionSupportProperties store support properties.
     * @return initialized storage root.
     */
    fun init(
      storeProjectionSupportProperties: StoreProjectionSupportProperties,
      managerFactory: EmbeddedStorageManagerFactory,
      foundationFactory: EmbeddedStorageFoundationFactory,
      queryBus: QueryBus
    ): StorageRoot {

      val eclipseStoreProperties = storeProjectionSupportProperties.storeProperties
      val storageManager = synchronized(this) {
        if (!isStorageExists(eclipseStoreProperties)) {
          logger.info { "[STORAGE-ROOT]: Storage is not initialized." }
          logger.info { "[STORAGE-ROOT]: Querying for a backup." }
          try {
            val backups = queryBus.queryForSnapshot(
              ownBackupLocation = eclipseStoreProperties.backupDirectory,
              storeKey = storeProjectionSupportProperties.storeKey
            )
            logger.info { "[STORAGE-ROOT]: Received ${backups.size} backup responses." }
            backups
              .map { result ->
                if (result.isFailure) {
                  throw result.exceptionOrNull()!!
                } else {
                  val snapshot = result.getOrThrow()
                  logger.info { "[STORAGE-ROOT]: Valid response from ${snapshot.location}." }
                  snapshot.bytes
                }
              }.firstOrNull() // in case we didn't get any
              ?.let { backup ->
                logger.info { "[STORAGE-ROOT]: Restoring backup to ${eclipseStoreProperties.storageDirectory}." }
                FileSystemHelper.uncompress(backupZipBytes = backup, targetDir = eclipseStoreProperties.storageDirectory)
              }
          } catch (e: Exception) {
            if (e is NoHandlerForQueryException) {
              logger.info { "[STORAGE-ROOT]: No backup was available." }
            } else {
              logger.error(e) { "[STORAGE-ROOT]: Something went wrong during backup retrieval." }
            }
          }
        }

        logger.info { "[STORAGE-ROOT]: Creating Storage and StorageManager." }
        val foundation = foundationFactory.createStorageFoundation(eclipseStoreProperties)
        managerFactory.createStorage(foundation, true) // autostart it right away
      }

      val root = if (storageManager.root() == null) {
        logger.info { "[STORAGE-ROOT]: No storage root found. Initializing new storage root." }
        StorageRoot().apply {
          storageManager.setRoot(this)
          storageManager.storeRoot()
        }
      } else {
        logger.info { "[STORAGE-ROOT]: Found existing storage root. Loading it..." }
        storageManager.root() as StorageRoot
      }

      // register manually
      logger.info { "[STORAGE-ROOT]: Activating backup ${storeProjectionSupportProperties.storeKey} query handler for others." }
      queryBus.registerQueryHandler(
        storeKey = storeProjectionSupportProperties.storeKey,
        instance = StorageQueryHandler(
          storeProjectionSupportProperties = storeProjectionSupportProperties
        )
      )

      root.storageManager = storageManager
      return root
    }
  }

  /**
   * Loads element by name.
   * @param name element name.
   * @return element or null
   */
  fun <T> getOrNull(name: String): T? = elements[name]?.let { this as T }

  /**
   * Loads element by name.
   * @param name element name.
   * @return element.
   * @throws NoSuchElementException if name is not known.
   */
  @Throws(NoSuchElementException::class)
  fun <T> get(name: String): T = if (elements.containsKey(name)) elements[name] as T else throw NoSuchElementException("Could not load element '$name'")


  /**
   * Checks whether an element is known.
   * @param name element name.
   * @return true if known.
   */
  fun contains(name: String): Boolean = elements.containsKey(name)

  /**
   * Saves an element.
   * @param name element name.
   * @param value element value.
   * @return value for further processing
   */
  fun <T : Any> set(name: String, value: T): T {
    elements[name] = value
    storageManager.store(elements) // storing the entire collection
    return value
  }

  /**
   * Unregisters an element.
   * @param name name under which the element was registered.
   * @return value if present or null if no element is available under given name.
   */
  fun <T : Any> unset(name: String): T? {
    return if (elements.containsKey(name)) {
      (elements.remove(name) as T).also {
        storageManager.store(elements)
      }
    } else {
      null
    }
  }

  /**
   * Store values somehow added to the store.
   * @param value value to store.
   */
  fun <T> append(value: T): T {
    storageManager.store(value) // storing only an element
    return value
  }

  /**
   * Shutdown the storage manager connected to this storage root.
   * @param reuseStorageManager flag indicating if the storage manager should be reused.
   *
   */
  fun dispose(reuseStorageManager: Boolean) {
    if (!reuseStorageManager) {
      storageManager.close()
    }
  }

  /**
   * Retrieves storage manager of current root.
   * @return storage manager.
   */
  fun getStorageManager(): EmbeddedStorageManager {
    if (!this::storageManager.isInitialized) {
      throw IllegalStateException("Storage manager is not initialized.")
    }
    return storageManager
  }
}
