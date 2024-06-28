package io.holixon.example.university.course.infrastructure.config

import io.holixon.axon.eclipsestore.root.StorageRoot
import org.eclipse.store.integrations.spring.boot.types.configuration.EclipseStoreProperties
import org.eclipse.store.integrations.spring.boot.types.factories.EmbeddedStorageFoundationFactory
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager
import org.eclipse.store.storage.types.StorageWriteControllerReadOnlyMode
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class PersistenceConfiguration {

  @Bean
  fun storageRoot(storageManager: EmbeddedStorageManager): StorageRoot {
    return StorageRoot.init(storageManager)
  }

  @Bean
  fun readOnlyStorageRootSupplier(
    eclipseStoreProperties: EclipseStoreProperties,
    embeddedStorageFoundationFactory: EmbeddedStorageFoundationFactory,
  ): () -> StorageRoot {
    val foundation = embeddedStorageFoundationFactory.createStorageFoundation(eclipseStoreProperties)
    foundation.writeController = StorageWriteControllerReadOnlyMode(foundation.writeController)
    return {
      StorageRoot.init(foundation.start())
    }
  }

}
