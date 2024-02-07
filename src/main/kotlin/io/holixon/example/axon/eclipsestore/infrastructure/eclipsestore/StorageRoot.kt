package io.holixon.example.axon.eclipsestore.infrastructure.eclipsestore

import mu.KLogging
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager


@Suppress("UNCHECKED_CAST")
class StorageRoot private constructor() {

  @Transient
  private lateinit var storageManager: EmbeddedStorageManager

  private val elements: MutableMap<String, Any> = mutableMapOf()

  companion object : KLogging() {
    /**
     * Initialize storage root for / from given storage manager.
     * @param   storageManager storage manager.
     * @return initialized storage root.
     */
    fun init(storageManager: EmbeddedStorageManager): StorageRoot {
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
}
