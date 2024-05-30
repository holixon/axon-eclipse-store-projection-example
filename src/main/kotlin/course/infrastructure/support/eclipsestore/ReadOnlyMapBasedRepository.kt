package io.holixon.example.university.course.infrastructure.support.eclipsestore

open class ReadOnlyMapBasedRepository<KEY : Any, VALUE : Any>(
        storageRootSupplier: () -> StorageRoot,
        config: EclipseStoreRepositoryConfig,
        idExtractor: (VALUE) -> KEY
) : PersistentMapBasedRepository<KEY, VALUE>(
  storageRootSupplier = storageRootSupplier,
  config = config,
  idExtractor = idExtractor
) {

  override fun save(id: KEY, value: VALUE): VALUE {
    throw UnsupportedOperationException("Not supported by read-only repository")
  }

  override fun save(value: VALUE): VALUE {
    throw UnsupportedOperationException("Not supported by read-only repository")
  }

  override fun deleteById(id: KEY): VALUE? {
    throw UnsupportedOperationException("Not supported by read-only repository")
  }

  override fun deleteAll() {
    throw UnsupportedOperationException("Not supported by read-only repository")
  }

  /**
   * Don't reuse storage manager, but obtain every time a new one from the supplier.
   */
  override fun reuseStorageManager(): Boolean = false
}
