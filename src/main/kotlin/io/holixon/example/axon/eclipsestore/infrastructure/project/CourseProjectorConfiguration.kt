package io.holixon.example.axon.eclipsestore.infrastructure.project

import io.holixon.example.axon.eclipsestore.adapter.out.project.CourseProjectorRepositoryImpl
import io.holixon.example.axon.eclipsestore.adapter.out.project.CourseProjectorRepository
import io.holixon.example.axon.eclipsestore.adapter.out.project.CourseProjector
import io.holixon.example.axon.eclipsestore.infrastructure.eclipsestore.StorageRoot
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
