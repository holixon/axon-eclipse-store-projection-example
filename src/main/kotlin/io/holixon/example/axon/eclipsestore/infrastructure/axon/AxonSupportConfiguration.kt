package io.holixon.example.axon.eclipsestore.infrastructure.axon

import io.holixon.example.axon.eclipsestore.infrastructure.eclipsestore.StorageRoot
import org.axonframework.config.EventProcessingConfiguration
import org.axonframework.eventhandling.tokenstore.TokenStore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AxonSupportConfiguration {

  @Bean
  fun processorSupport(eventProcessingConfiguration: EventProcessingConfiguration): ProcessorSupport {
    return ProcessorSupport(eventProcessingConfiguration = eventProcessingConfiguration)
  }

  @Bean
  fun tokenStore(storageRoot: StorageRoot): TokenStore {
    return EclipseStoreTokenStore("courses-application", storageRoot)
  }

}
