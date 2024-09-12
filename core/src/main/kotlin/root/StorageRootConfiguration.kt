package io.holixon.axon.eclipsestore.root

import io.holixon.axon.eclipsestore.tokenstore.ConfigurationSupplier
import mu.KLogging
import org.axonframework.axonserver.connector.AxonServerConfiguration
import org.eclipse.store.integrations.spring.boot.types.configuration.EclipseStoreProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import java.nio.file.Path

@Configuration
@ComponentScan
@EnableConfigurationProperties(value = [StoreProjectionSupportProperties::class])
class StorageRootConfiguration {
  companion object : KLogging()

  @Bean
  fun configurationSupplier(
    axonServerConfiguration: AxonServerConfiguration,
    eclipseStoreProperties: EclipseStoreProperties
  ): ConfigurationSupplier {
    val storageId = Path.of(eclipseStoreProperties.storageDirectory).normalize().toString()
    return object : ConfigurationSupplier {
      override fun clientId(): String = axonServerConfiguration.clientId
      override fun storageId(): String = storageId
    }
  }
}
