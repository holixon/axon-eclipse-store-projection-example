package io.holixon.example.university.course.infrastructure.support.eclipsestore

open class PersistentMapBasedRepository<KEY : Any, VALUE : Any>(
        storageRootSupplier: () -> StorageRoot,
        config: EclipseStoreRepositoryConfig,
        private val idExtractor: (VALUE) -> KEY = noIdExtractor()
) : BaseStoreRepository<MutableMap<KEY, VALUE>>(
  storageRootSupplier = storageRootSupplier,
  config = config
) {

  companion object {
    @JvmStatic
    fun <KEY, VALUE> noIdExtractor(): (VALUE) -> KEY = { throw NotImplementedError("No extractor for ids provided.") }
  }


  override fun initializeModelInstance(): MutableMap<KEY, VALUE> = mutableMapOf()

  fun findById(id: KEY): VALUE {
    return getModelInstance()[id] ?: throw NoSuchElementException("No element for id $id was found.")
  }

  fun findByIdOrNull(id: KEY): VALUE? {
    return getModelInstance()[id]
  }

  fun findAll(): List<VALUE> {
    return getModelInstance().values.toList()
  }

  open fun save(id: KEY, value: VALUE): VALUE {
    val instance = getModelInstance()
    instance[id] = value
    modifyModelInstance(instance)
    return value
  }

  open fun save(value: VALUE): VALUE {
    val id = try {
      idExtractor.invoke(value)
    } catch (e: NotImplementedError) {
      throw IllegalArgumentException("Could not extract id from value $value, no idExtractor was provided.")
    }
    return save(id = id, value = value)
  }

  open fun deleteById(id: KEY): VALUE? {
    val instance = getModelInstance()
    val value = instance.remove(id)
    modifyModelInstance(instance)
    return value
  }

  open fun deleteAll() {
    val instance = getModelInstance()
    instance.clear()
    modifyModelInstance(instance)
  }

  fun countAll(): Int {
    val instance = getModelInstance()
    return instance.size
  }
}
