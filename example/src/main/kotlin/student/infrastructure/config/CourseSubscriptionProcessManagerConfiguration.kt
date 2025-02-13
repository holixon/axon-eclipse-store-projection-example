package io.holixon.example.university.student.infrastructure.config

import io.github.oshai.kotlinlogging.KotlinLogging
import io.holixon.axon.eclipsestore.root.StorageRootSupplier
import io.holixon.axon.eclipsestore.tokenstore.ConfigurationSupplier
import io.holixon.axon.eclipsestore.tokenstore.EclipseStoreTokenStore
import io.holixon.example.university.global.config.AxonSupportConfiguration.Companion.TOKEN_STORE_NAME
import io.holixon.example.university.student.infrastructure.adapter.`in`.event.CourseSubscriptionProcessManager
import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

private val logger = KotlinLogging.logger {}

@Configuration
class CourseSubscriptionProcessManagerConfiguration {

  @Autowired
  fun configureStudentProcessor(
    eventProcessingConfigurer: EventProcessingConfigurer,
    storageRootSupplier: StorageRootSupplier,
    configurationSupplier: ConfigurationSupplier
  ) {
    logger.info { "Initialized course subscription projection. Processor is not auto-started." }
    eventProcessingConfigurer.registerTrackingEventProcessorConfiguration { _ ->
      TrackingEventProcessorConfiguration.forSingleThreadedProcessing().andAutoStart(false)
    }
    eventProcessingConfigurer.registerTokenStore(CourseSubscriptionProcessManager.GROUP) {
      EclipseStoreTokenStore(name = TOKEN_STORE_NAME, configurationSupplier = configurationSupplier, storageRootSupplier = storageRootSupplier)
    }
  }
}
