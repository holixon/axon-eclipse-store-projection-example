package io.holixon.axon.eclipsestore.repository

import io.holixon.axon.eclipsestore.root.StorageRoot
import java.util.concurrent.ConcurrentHashMap

/**
 * Full access repository backed by a hashmap.
 * @param KEY identity type.
 * @param VALUE entity type.
 * @param storageRootSupplier supplier for the storage root, passed from the infrastructure.
 * @param config repository configuration (e.g. for providing the repository name).
 * @param idExtractor extractor function to retrieve identity from the entity.
 */
open class PersistentMapBasedRepository<KEY : Any, VALUE : Any>(
  storageRootSupplier: () -> StorageRoot,
  config: EclipseStoreRepositoryConfig,
  private val idExtractor: (VALUE) -> KEY = noIdExtractor()
) : BaseStoreRepository<MutableMap<KEY, VALUE>>(
  storageRootSupplier = storageRootSupplier,
  config = config
), FullAccessRepository<KEY, VALUE> {

  companion object {
    @JvmStatic
    fun <KEY, VALUE> noIdExtractor(): (VALUE) -> KEY = { throw NotImplementedError("No extractor for ids provided.") }
  }


  override fun initializeModelInstance(): ConcurrentHashMap<KEY, VALUE> = ConcurrentHashMap()

  override fun findById(id: KEY): VALUE {
    return getModelInstance()[id] ?: throw NoSuchElementException("No element for id $id was found.")
  }

  override fun findByIdOrNull(id: KEY): VALUE? {
    return getModelInstance()[id]
  }

  override fun findAll(): List<VALUE> {
    return getModelInstance().values.toList()
  }

  open fun save(id: KEY, value: VALUE): VALUE {
    val instance = getModelInstance()
    instance[id] = value
    modifyModelInstance(instance)
    return value
  }

  override fun save(value: VALUE): VALUE {
    val id = try {
      idExtractor.invoke(value)
    } catch (e: NotImplementedError) {
      throw IllegalArgumentException("Could not extract id from value $value, no idExtractor was provided.")
    }
    return save(id = id, value = value)
  }

  override fun deleteById(id: KEY): VALUE? {
    val instance = getModelInstance()
    val value = instance.remove(id)
    modifyModelInstance(instance)
    return value
  }

  override fun deleteAll() {
    val instance = getModelInstance()
    instance.clear()
    modifyModelInstance(instance)
  }

  override fun countAll(): Int {
    val instance = getModelInstance()
    return instance.size
  }
}
