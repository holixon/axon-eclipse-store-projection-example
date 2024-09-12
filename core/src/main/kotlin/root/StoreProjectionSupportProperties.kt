package io.holixon.axon.eclipsestore.root

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(value = "holixon.axon-store.support")
data class StoreProjectionSupportProperties(
  val storeKey: String
)
