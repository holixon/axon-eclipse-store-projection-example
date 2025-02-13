package io.holixon.example.university.course.infrastructure.config

import io.github.oshai.kotlinlogging.KotlinLogging
import io.holixon.axon.eclipsestore.root.StorageRootSupplier
import io.holixon.axon.eclipsestore.tokenstore.ConfigurationSupplier
import io.holixon.axon.eclipsestore.tokenstore.EclipseStoreTokenStore
import io.holixon.example.university.course.infrastructure.adapter.out.projector.CourseProjector
import io.holixon.example.university.course.infrastructure.adapter.out.projector.CourseProjectorRepository
import io.holixon.example.university.course.infrastructure.adapter.out.projector.CourseProjectorRepositoryImpl
import io.holixon.example.university.course.infrastructure.adapter.out.query.impl.CourseProjectionRepository
import io.holixon.example.university.course.infrastructure.adapter.out.query.impl.CourseProjectionRepositoryImpl
import io.holixon.example.university.global.config.AxonSupportConfiguration.Companion.TOKEN_STORE_NAME
import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

private val logger = KotlinLogging.logger {}

@Configuration
class CourseProjectionConfiguration {

  @Autowired
  fun configureCoursesProcessor(
    eventProcessingConfigurer: EventProcessingConfigurer,
    storageRootSupplier: StorageRootSupplier,
    configurationSupplier: ConfigurationSupplier
  ) {

    logger.info { "Initialized course projection. Processor is not auto-started." }

    eventProcessingConfigurer.registerTrackingEventProcessorConfiguration { _ ->
      TrackingEventProcessorConfiguration.forSingleThreadedProcessing().andAutoStart(false)
    }
    eventProcessingConfigurer.registerTokenStore(CourseProjector.GROUP) {
      EclipseStoreTokenStore(name = TOKEN_STORE_NAME, configurationSupplier = configurationSupplier, storageRootSupplier = storageRootSupplier)
    }
  }

  @Bean
  fun courseProjectorRepository(
    storageRootSupplier: StorageRootSupplier
  ): CourseProjectorRepository = CourseProjectorRepositoryImpl(
    storageRootSupplier = storageRootSupplier
  )

  @Bean
  fun courseProjectionReadOnlyRepository(
    storageRootSupplier: StorageRootSupplier
  ): CourseProjectionRepository = CourseProjectionRepositoryImpl(
    storageRootSupplier = storageRootSupplier
  )

}
