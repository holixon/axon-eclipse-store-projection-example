package io.holixon.example.university.course.infrastructure.config

import io.holixon.example.university.course.infrastructure.adapter.out.query.impl.CourseProjectionRepository
import io.holixon.example.university.course.infrastructure.adapter.out.query.impl.CourseProjectionRepositoryImpl
import io.holixon.example.university.course.infrastructure.support.eclipsestore.StorageRoot
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CourseProjectionConfiguration {

  @Bean
  fun courseProjectionReadOnlyRepository(
    storageRoot: StorageRoot
  ): CourseProjectionRepository = CourseProjectionRepositoryImpl(
    storageRootSupplier = { storageRoot }
  )

}
