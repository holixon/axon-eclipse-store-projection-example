package io.holixon.example.axon.eclipsestore.infrastructure.query

import io.holixon.example.axon.eclipsestore.adapter.out.query.impl.CourseProjectionRepository
import io.holixon.example.axon.eclipsestore.adapter.out.query.impl.CourseProjectionRepositoryImpl
import io.holixon.example.axon.eclipsestore.infrastructure.eclipsestore.StorageRoot
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
