package io.holixon.example.axon.eclipsestore.infrastructure.query.model

import io.holixon.example.axon.eclipsestore.adapter.out.query.impl.CourseProjectionRepository
import io.holixon.example.axon.eclipsestore.adapter.out.query.impl.CourseProjector
import io.holixon.example.axon.eclipsestore.infrastructure.EclipseStoreTokenStore
import io.holixon.example.axon.eclipsestore.infrastructure.StorageRoot
import org.axonframework.config.EventProcessingConfigurer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CourseAdapterConfiguration {

  @Autowired
  fun configureCoursesProcessor(eventProcessingConfigurer: EventProcessingConfigurer, storageRoot: StorageRoot) {
    val coursesTokenStore = EclipseStoreTokenStore(CourseProjector.GROUP, storageRoot)
    eventProcessingConfigurer.registerTokenStore(CourseProjector.GROUP) { coursesTokenStore }
  }

  @Bean
  fun courseProjectionRepository(
    storageRoot: StorageRoot
  ): CourseProjectionRepository = CourseProjectionRepositoryImpl(
    storageRoot = storageRoot,
    processingGroupName = CourseProjector.GROUP
  )
}
