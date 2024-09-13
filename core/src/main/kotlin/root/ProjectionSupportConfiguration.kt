package io.holixon.axon.eclipsestore.root

import io.holixon.axon.eclipsestore.tokenstore.ConfigurationSupplier
import mu.KLogging
import org.axonframework.axonserver.connector.AxonServerConfiguration
import org.eclipse.store.integrations.spring.boot.types.configuration.EclipseStoreProperties
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import java.nio.file.Path

@Configuration
@ComponentScan
@EnableConfigurationProperties(value = [ProjectionSupportProperties::class])
class ProjectionSupportConfiguration {
  companion object : KLogging()

  @Bean
  fun configurationSupplier(
    axonServerConfiguration: AxonServerConfiguration,
    projectionSupportProperties: ProjectionSupportProperties
  ): ConfigurationSupplier {
    val storageId = Path.of(projectionSupportProperties.store.storageDirectory).normalize().toString()
    return object : ConfigurationSupplier {
      override fun clientId(): String = axonServerConfiguration.clientId
      override fun storageId(): String = storageId
    }
  }

  @Bean
  @Qualifier("axonEclipseStoreProperties")
  fun axonEclipseStoreProperties(projectionSupportProperties: ProjectionSupportProperties): EclipseStoreProperties = projectionSupportProperties.store
}
