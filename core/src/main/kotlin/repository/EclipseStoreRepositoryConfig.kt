package io.holixon.axon.eclipsestore.repository

/**
 * Configuration of the repository.
 * @param name name of the repository.
 */
data class EclipseStoreRepositoryConfig(
  val name: String
) {

  companion object {
    const val DEFAULT = "__default"

    /**
     * Default configuration.
     */
    @JvmStatic
    fun default(): EclipseStoreRepositoryConfig = EclipseStoreRepositoryConfig(name = DEFAULT)
  }

}
