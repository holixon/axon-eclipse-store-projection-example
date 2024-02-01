package io.holixon.example.axon.eclipsestore.infrastructure

import io.holixon.example.axon.eclipsestore.adapter.out.query.model.CourseProjector
import org.axonframework.config.EventProcessingConfigurer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

@Configuration
class CoursesAdapterConfiguration {
  @Autowired
  fun configureCoursesProcessor(eventProcessingConfigurer: EventProcessingConfigurer, storageRoot: StorageRoot) {
    val coursesTokenStore = EclipseStoreTokenStore(CourseProjector.GROUP, storageRoot)
    eventProcessingConfigurer.registerTokenStore(CourseProjector.GROUP) { coursesTokenStore }
  }
}
