package io.holixon.axon.eclipsestore.tokenstore

/**
 * Retrieves configuration parameters.
 */
interface ConfigurationSupplier {

  /**
   * Retrieves node client identifier.
   */
  fun clientId(): String

  /**
   * Retrieve storage identifier.
   */
  fun storageId(): String

  /**
   * Retrieve identifier for the store.
   */
  fun geCurrentNodeIdentifier() = "${clientId()}:/${storageId()}"
}
