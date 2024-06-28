package io.holixon.axon.eclipsestore.repository

import io.holixon.axon.eclipsestore.root.StorageRoot
import jakarta.annotation.PostConstruct

/**
 * Base class to implement repositories.
 */
abstract class BaseStoreRepository<T : Any>(
        private val storageRootSupplier: () -> StorageRoot,
        val config: EclipseStoreRepositoryConfig = EclipseStoreRepositoryConfig.default()
) {

  /**
   * Stores current repository into store.
   */
  @PostConstruct
  fun connect() {
    val repositoryName = getRepositoryName()
    val storageRoot = storageRootSupplier.invoke()
    // initializes
    if (!storageRoot.contains(repositoryName)) {
      storageRoot.set(repositoryName, initializeModelInstance())
    }
  }

  /**
   * Initializes a new model instance if none is found in the store.
   * @return new model instance.
   */
  abstract fun initializeModelInstance(): T

  /**
   * Retrieves a model instance.
   * @return a model instance loaded from root.
   */
  fun getModelInstance(): T {
    val storageRoot = storageRootSupplier.invoke()
    return storageRoot.get<T>(getRepositoryName()).also {
      storageRoot.dispose(reuseStorageManager())
    }
  }

  /**
   * Modifies model instance in the storage.
   * @param modelInstance new model instance.
   * @return model instance for further use.
   */
  fun modifyModelInstance(modelInstance: T): T {
    val storageRoot = storageRootSupplier.invoke()
    storageRoot.append(value = modelInstance)
    storageRoot.dispose(reuseStorageManager())
    return modelInstance
  }

  /**
   * Retrieves repository name.
   * @return name for this repository.
   */
  fun getRepositoryName(): String = if (config.name == EclipseStoreRepositoryConfig.DEFAULT) {
    this.javaClass.simpleName
  } else {
    config.name
  }

  /**
   * Flag indicating if the storage root should be reused.
   * @return flag, defaults to true.
   */
  open fun reuseStorageManager(): Boolean = true
}