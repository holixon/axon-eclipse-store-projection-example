package org.eclipse.store.storage.restservice.springboot

import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager
import org.eclipse.store.storage.restadapter.types.StorageRestAdapter
import org.eclipse.store.storage.restservice.types.StorageRestService
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan

@AutoConfiguration
@ComponentScan
class StorageRestServiceSpringAutoConfiguration {

  //   @Bean(initMethod = "start", destroyMethod = "stop")
  fun storageRestService(storage: EmbeddedStorageManager): StorageRestService {
    return StorageDataRestRestController(StorageRestAdapter.New(storage))
  }

  @Bean
  fun storageRestAdapter(storage: EmbeddedStorageManager): StorageRestAdapter {
    return StorageRestAdapter.New(storage)
  }
}
