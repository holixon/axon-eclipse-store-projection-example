package io.holixon.example.university.course.infrastructure.config

import io.holixon.axon.eclipsestore.root.StorageRoot
import io.holixon.example.university.course.infrastructure.adapter.out.projector.CourseProjector
import io.holixon.example.university.course.infrastructure.adapter.out.projector.CourseProjectorRepository
import io.holixon.example.university.course.infrastructure.adapter.out.projector.CourseProjectorRepositoryImpl
import io.holixon.example.university.course.infrastructure.adapter.out.query.impl.CourseProjectionRepository
import io.holixon.example.university.course.infrastructure.adapter.out.query.impl.CourseProjectionRepositoryImpl
import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.eventhandling.tokenstore.TokenStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CourseProjectionConfiguration {

  @Autowired
  fun configureCoursesProcessor(
    eventProcessingConfigurer: EventProcessingConfigurer,
    tokenStore: TokenStore
  ) {
    eventProcessingConfigurer.registerTokenStore(CourseProjector.GROUP) { tokenStore }
  }

  @Bean
  fun courseProjectorRepository(
    storageRoot: StorageRoot
  ): CourseProjectorRepository = CourseProjectorRepositoryImpl(
    storageRootSupplier = { storageRoot }
  )

  @Bean
  fun courseProjectionReadOnlyRepository(
    storageRoot: StorageRoot
  ): CourseProjectionRepository = CourseProjectionRepositoryImpl(
    storageRootSupplier = { storageRoot }
  )

}
