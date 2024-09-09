package io.holixon.example.university.global.config

import io.holixon.axon.eclipsestore.root.QueryingStorageManagerConfiguration
import io.holixon.axon.eclipsestore.root.StorageRootSupplier
import io.holixon.axon.eclipsestore.root.StorageManagerStorageRootSupplierImpl
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import


@Configuration
@Import(QueryingStorageManagerConfiguration::class)
class EclipseStoreConfiguration {

  @Bean
  fun storageRootSupplier(storageManager: EmbeddedStorageManager): StorageRootSupplier = StorageManagerStorageRootSupplierImpl(storageManager)
}
