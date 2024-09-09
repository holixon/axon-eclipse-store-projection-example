package io.holixon.example.university.course.infrastructure.config

import io.holixon.axon.eclipsestore.root.StorageRootSupplier
import io.holixon.axon.eclipsestore.tokenstore.EclipseStoreTokenStore
import io.holixon.example.university.course.infrastructure.adapter.out.projector.CourseProjector
import io.holixon.example.university.course.infrastructure.adapter.out.projector.CourseProjectorRepository
import io.holixon.example.university.course.infrastructure.adapter.out.projector.CourseProjectorRepositoryImpl
import io.holixon.example.university.course.infrastructure.adapter.out.query.impl.CourseProjectionRepository
import io.holixon.example.university.course.infrastructure.adapter.out.query.impl.CourseProjectionRepositoryImpl
import io.holixon.example.university.global.config.AxonSupportConfiguration.Companion.TOKEN_STORE_NAME
import mu.KLogging
import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CourseProjectionConfiguration {

  companion object : KLogging()

  @Autowired
  fun configureCoursesProcessor(eventProcessingConfigurer: EventProcessingConfigurer, storageRootSupplier: StorageRootSupplier) {

    logger.info { "Initialized course projection. Processor is not auto-started." }

    eventProcessingConfigurer.registerTrackingEventProcessorConfiguration { _ ->
      TrackingEventProcessorConfiguration.forSingleThreadedProcessing().andAutoStart(false)
    }
    eventProcessingConfigurer.registerTokenStore(CourseProjector.GROUP) {
      EclipseStoreTokenStore(name = TOKEN_STORE_NAME, storageRootSupplier = storageRootSupplier)
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
