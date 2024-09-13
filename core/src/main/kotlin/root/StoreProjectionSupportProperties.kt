package io.holixon.axon.eclipsestore.root

import org.eclipse.store.integrations.spring.boot.types.configuration.EclipseStoreProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty

@ConfigurationProperties(value = "holixon.axon-store.support")
data class StoreProjectionSupportProperties(
  val storeKey: String,
  @NestedConfigurationProperty
  val storeProperties: EclipseStoreProperties = EclipseStoreProperties()
)
