package io.holixon.example.university.course.infrastructure.config

import io.holixon.example.university.course.infrastructure.adapter.out.projector.CourseProjectorRepositoryImpl
import io.holixon.example.university.course.infrastructure.adapter.out.projector.CourseProjectorRepository
import io.holixon.example.university.course.infrastructure.adapter.out.projector.CourseProjector
import io.holixon.example.university.course.infrastructure.support.eclipsestore.StorageRoot
import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.eventhandling.tokenstore.TokenStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CourseProjectorConfiguration {

  @Autowired
  fun configureCoursesProcessor(eventProcessingConfigurer: EventProcessingConfigurer, tokenStore: TokenStore) {
    eventProcessingConfigurer.registerTokenStore(CourseProjector.GROUP) { tokenStore }
  }

  @Bean
  fun courseProjectorRepository(
    storageRoot: StorageRoot
  ): CourseProjectorRepository = CourseProjectorRepositoryImpl(
    storageRootSupplier = { storageRoot }
  )

}
