package io.holixon.axon.eclipsestore.repository

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