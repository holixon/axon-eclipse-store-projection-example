package io.holixon.example.axon.eclipsestore.infrastructure.eclipsestore

import org.eclipse.store.integrations.spring.boot.types.EclipseStoreSpringBoot
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import


@Configuration
@Import(value = [EclipseStoreSpringBoot::class])
class PersistenceConfiguration {

  @Bean
  fun storageRoot(storageManager: EmbeddedStorageManager): StorageRoot {
    return StorageRoot.init(storageManager)
  }
}
