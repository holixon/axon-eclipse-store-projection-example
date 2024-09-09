package io.holixon.axon.eclipsestore.root

import io.holixon.axon.eclipsestore.backup.queryForSnapshot
import org.axonframework.queryhandling.QueryGateway
import org.eclipse.store.integrations.spring.boot.types.configuration.EclipseStoreProperties
import org.eclipse.store.integrations.spring.boot.types.factories.EmbeddedStorageFoundationFactory
import org.eclipse.store.integrations.spring.boot.types.factories.EmbeddedStorageManagerFactory
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueryingStorageManagerConfiguration {

  @Bean
  fun queryingEmbeddedStorageManager(
    eclipseStoreProperties: EclipseStoreProperties,
    foundationFactory: EmbeddedStorageFoundationFactory,
    managerFactory: EmbeddedStorageManagerFactory,
    queryGateway: QueryGateway
  ): EmbeddedStorageManager {

    if (storageNotPresent(eclipseStoreProperties = eclipseStoreProperties, queryGateway = queryGateway)) {
      getStorageCopy()
    }

    // Create a new StorageFoundation
    val storageFoundation = foundationFactory.createStorageFoundation(eclipseStoreProperties);

    // Modify the storageFoundation
    //storageFoundation.onConnectionFoundation(f -> f.someOperation);
    // Create a new StorageManager
    return managerFactory.createStorage(storageFoundation, eclipseStoreProperties.isAutoStart);
  }

  private fun getStorageCopy(queryGateway: QueryGateway) {
    val result = queryGateway.queryForSnapshot(null)
  }

  fun storageNotPresent(eclipseStoreProperties: EclipseStoreProperties): Boolean {
    File(eclipseStoreProperties.storageDirectory)
    return false
  }
}
