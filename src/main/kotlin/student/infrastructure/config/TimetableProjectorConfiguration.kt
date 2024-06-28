package io.holixon.example.university.student.infrastructure.config

import io.holixon.example.university.course.infrastructure.support.eclipsestore.StorageRoot
import io.holixon.example.university.student.infrastructure.adapter.out.projector.TimetableProjector
import io.holixon.example.university.student.infrastructure.adapter.out.projector.TimetableProjectorRepository
import io.holixon.example.university.student.infrastructure.adapter.out.projector.TimetableProjectorRepositoryImpl
import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.eventhandling.tokenstore.TokenStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TimetableProjectorConfiguration {

  @Autowired
  fun configureTimetableProcessor(eventProcessingConfigurer: EventProcessingConfigurer, tokenStore: TokenStore) {
    eventProcessingConfigurer.registerTokenStore(TimetableProjector.GROUP) { tokenStore }
  }

  @Bean
  fun timetableProjectorRepository(
    storageRoot: StorageRoot
  ): TimetableProjectorRepository = TimetableProjectorRepositoryImpl(
    storageRootSupplier = { storageRoot }
  )

}
