package io.holixon.example.university.student.infrastructure.config

import io.holixon.example.university.course.infrastructure.support.eclipsestore.StorageRoot
import io.holixon.example.university.student.infrastructure.adapter.out.projector.StudentProjector
import io.holixon.example.university.student.infrastructure.adapter.out.projector.StudentProjectorRepository
import io.holixon.example.university.student.infrastructure.adapter.out.projector.StudentProjectorRepositoryImpl
import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.eventhandling.tokenstore.TokenStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MatriculationProjectorConfiguration {

  @Autowired
  fun configureStudentProcessor(eventProcessingConfigurer: EventProcessingConfigurer, tokenStore: TokenStore) {
    eventProcessingConfigurer.registerTokenStore(StudentProjector.GROUP) { tokenStore }
  }

  @Bean
  fun studentProjectorRepository(
    storageRoot: StorageRoot
  ): StudentProjectorRepository = StudentProjectorRepositoryImpl(
    storageRootSupplier = { storageRoot }
  )

}
