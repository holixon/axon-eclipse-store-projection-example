package io.holixon.axon.eclipsestore.root

import mu.KLogging
import org.eclipse.store.integrations.spring.boot.types.configuration.EclipseStoreProperties
import org.eclipse.store.integrations.spring.boot.types.factories.EmbeddedStorageFoundationFactory
import org.eclipse.store.integrations.spring.boot.types.factories.EmbeddedStorageManagerFactory
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan
class ScanningStorageManagerConfiguration {

  companion object : KLogging()

  @Bean
  fun notStartedEmbeddedStorageManager(
    eclipseStoreProperties: EclipseStoreProperties,
    foundationFactory: EmbeddedStorageFoundationFactory,
    managerFactory: EmbeddedStorageManagerFactory,
  ): EmbeddedStorageManager {

    // Create a new StorageFoundation
    val storageFoundation = foundationFactory.createStorageFoundation(eclipseStoreProperties)

    // Modify the storageFoundation
    storageFoundation.onConnectionFoundation {
      logger.info { "ECLIPSE-STORE-SUPPORT: FOUNDATION CONNECTED" }
    }

    // Create a new StorageManager
    return managerFactory.createStorage(storageFoundation, false)
  }
}
