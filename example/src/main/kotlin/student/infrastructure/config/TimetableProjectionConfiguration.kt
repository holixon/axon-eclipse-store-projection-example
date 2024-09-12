package io.holixon.example.university.student.infrastructure.config

import io.holixon.axon.eclipsestore.root.StorageRootSupplier
import io.holixon.axon.eclipsestore.tokenstore.ConfigurationSupplier
import io.holixon.axon.eclipsestore.tokenstore.EclipseStoreTokenStore
import io.holixon.example.university.global.config.AxonSupportConfiguration.Companion.TOKEN_STORE_NAME
import io.holixon.example.university.student.infrastructure.adapter.out.projector.TimetableProjector
import io.holixon.example.university.student.infrastructure.adapter.out.projector.TimetableProjectorRepository
import io.holixon.example.university.student.infrastructure.adapter.out.projector.TimetableProjectorRepositoryImpl
import io.holixon.example.university.student.infrastructure.adapter.out.query.impl.TimetableProjectionRepository
import io.holixon.example.university.student.infrastructure.adapter.out.query.impl.TimetableProjectionRepositoryImpl
import mu.KLogging
import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TimetableProjectionConfiguration {

  companion object : KLogging()

  @Autowired
  fun configureTimetableProcessor(
    eventProcessingConfigurer: EventProcessingConfigurer,
    storageRootSupplier: StorageRootSupplier,
    configurationSupplier: ConfigurationSupplier
  ) {
    logger.info { "Initialized student projection. Processor is not auto-started." }
    eventProcessingConfigurer.registerTrackingEventProcessorConfiguration { _ ->
      TrackingEventProcessorConfiguration.forSingleThreadedProcessing().andAutoStart(false)
    }
    eventProcessingConfigurer.registerTokenStore(TimetableProjector.GROUP) {
      EclipseStoreTokenStore(name = TOKEN_STORE_NAME, storageRootSupplier = storageRootSupplier, configurationSupplier = configurationSupplier)
    }
  }

  @Bean
  fun timetableProjectionReadOnlyRepository(
    storageRootSupplier: StorageRootSupplier
  ): TimetableProjectionRepository = TimetableProjectionRepositoryImpl(
    storageRootSupplier = storageRootSupplier
  )

  @Bean
  fun timetableProjectorRepository(
    storageRootSupplier: StorageRootSupplier
  ): TimetableProjectorRepository = TimetableProjectorRepositoryImpl(
    storageRootSupplier = storageRootSupplier
  )


}
