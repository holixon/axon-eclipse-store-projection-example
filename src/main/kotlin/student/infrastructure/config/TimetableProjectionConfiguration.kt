package io.holixon.example.university.student.infrastructure.config

import io.holixon.example.university.course.infrastructure.support.eclipsestore.StorageRoot
import io.holixon.example.university.student.infrastructure.adapter.out.query.impl.MatriculationProjectionRepository
import io.holixon.example.university.student.infrastructure.adapter.out.query.impl.MatriculationProjectionRepositoryImpl
import io.holixon.example.university.student.infrastructure.adapter.out.query.impl.TimetableProjectionRepository
import io.holixon.example.university.student.infrastructure.adapter.out.query.impl.TimetableProjectionRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TimetableProjectionConfiguration {

  @Bean
  fun timetableProjectionReadOnlyRepository(
    storageRoot: StorageRoot
  ): TimetableProjectionRepository = TimetableProjectionRepositoryImpl(
    storageRootSupplier = { storageRoot }
  )

}
