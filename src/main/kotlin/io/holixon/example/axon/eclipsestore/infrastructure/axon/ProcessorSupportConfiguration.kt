package io.holixon.example.axon.eclipsestore.infrastructure.axon

import org.axonframework.config.EventProcessingConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProcessorSupportConfiguration {

  @Bean
  fun processorSupport(eventProcessingConfiguration: EventProcessingConfiguration) = ProcessorSupport(eventProcessingConfiguration = eventProcessingConfiguration)
}
