package io.holixon.example.axon.eclipsestore.infrastructure.eclipsestore

import jakarta.annotation.PostConstruct

/**
 * Base class to implement repositories.
 */
abstract class BaseStoreRepository<T : Any>(
  private val storageRoot: StorageRoot,
  val config: EclipseStoreRepositoryConfig = EclipseStoreRepositoryConfig.default()
) {

  /**
   * Stores current repository into store.
   */
  @PostConstruct
  fun connect() {
    val repositoryName = getRepositoryName();
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
  fun getModelInstance(): T = storageRoot.get(getRepositoryName())

  /**
   * Modifies model instance in the storage.
   * @param modelInstance new model instance.
   * @return model instance for further use.
   */
  fun modifyModelInstance(modelInstance: T): T {
    storageRoot.append(value = modelInstance)
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
}

data class EclipseStoreRepositoryConfig(
  val name: String
) {

  companion object {
    const val DEFAULT = "__default"

    /**
     * Default configuration.
     */
    @JvmStatic
    fun default(): EclipseStoreRepositoryConfig = EclipseStoreRepositoryConfig(
      name = DEFAULT
    )
  }

}
