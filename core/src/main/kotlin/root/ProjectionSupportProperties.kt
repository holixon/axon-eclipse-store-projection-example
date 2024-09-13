package io.holixon.axon.eclipsestore.root

import io.holixon.axon.eclipsestore.root.ProjectionSupportProperties.Companion.PREFIX
import org.eclipse.store.integrations.spring.boot.types.configuration.EclipseStoreProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty

@ConfigurationProperties(value = PREFIX)
data class ProjectionSupportProperties(
  val storeKey: String,
  val instanceKey: String,
  @NestedConfigurationProperty
  val store: EclipseStoreProperties = EclipseStoreProperties()
) {
  companion object {
    const val PREFIX = "axon-projection-support"
  }
}
