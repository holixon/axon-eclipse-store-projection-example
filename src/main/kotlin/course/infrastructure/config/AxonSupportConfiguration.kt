package io.holixon.example.university.course.infrastructure.config

import io.holixon.example.university.course.infrastructure.support.axon.EclipseStoreTokenStore
import io.holixon.example.university.course.infrastructure.support.axon.ProcessorSupport
import io.holixon.example.university.course.infrastructure.support.eclipsestore.StorageRoot
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
    return EclipseStoreTokenStore("university-application", storageRoot)
  }

}
