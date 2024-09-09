package io.holixon.example.university.student.infrastructure.config

import io.holixon.axon.eclipsestore.root.StorageRootSupplier
import io.holixon.axon.eclipsestore.tokenstore.EclipseStoreTokenStore
import io.holixon.example.university.global.config.AxonSupportConfiguration.Companion.TOKEN_STORE_NAME
import io.holixon.example.university.student.infrastructure.adapter.out.projector.StudentProjector
import io.holixon.example.university.student.infrastructure.adapter.out.projector.StudentProjectorRepository
import io.holixon.example.university.student.infrastructure.adapter.out.projector.StudentProjectorRepositoryImpl
import io.holixon.example.university.student.infrastructure.adapter.out.query.impl.MatriculationProjectionRepository
import io.holixon.example.university.student.infrastructure.adapter.out.query.impl.MatriculationProjectionRepositoryImpl
import mu.KLogging
import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MatriculationProjectionConfiguration {

  companion object : KLogging()

  @Autowired
  fun configureStudentProcessor(eventProcessingConfigurer: EventProcessingConfigurer, storageRootSupplier: StorageRootSupplier) {
    logger.info { "Initialized student projection. Processor is not auto-started." }
    eventProcessingConfigurer.registerTrackingEventProcessorConfiguration { _ ->
      TrackingEventProcessorConfiguration.forSingleThreadedProcessing().andAutoStart(false)
    }
    eventProcessingConfigurer.registerTokenStore(StudentProjector.GROUP) {
      EclipseStoreTokenStore(name = TOKEN_STORE_NAME, storageRootSupplier = storageRootSupplier)
    }
  }

  @Bean
  fun matriculationProjectionReadOnlyRepository(
    storageRootSupplier: StorageRootSupplier
  ): MatriculationProjectionRepository = MatriculationProjectionRepositoryImpl(
    storageRootSupplier = storageRootSupplier
  )

  @Bean
  fun studentProjectorRepository(
    storageRootSupplier: StorageRootSupplier
  ): StudentProjectorRepository = StudentProjectorRepositoryImpl(
    storageRootSupplier = storageRootSupplier
  )


}
