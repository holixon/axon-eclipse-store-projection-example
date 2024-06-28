package io.holixon.example.university.student.infrastructure.adapter.out.projector

import io.holixon.example.university.course.infrastructure.support.eclipsestore.EclipseStoreRepositoryConfig
import io.holixon.example.university.course.infrastructure.support.eclipsestore.PersistentMapBasedRepository
import io.holixon.example.university.course.infrastructure.support.eclipsestore.StorageRoot
import io.holixon.example.university.student.domain.query.Matriculation
import io.holixon.example.university.student.domain.query.Timetable

class TimetableProjectorRepositoryImpl(storageRootSupplier: () -> StorageRoot) : TimetableProjectorRepository, PersistentMapBasedRepository<String, Timetable>(
  storageRootSupplier = storageRootSupplier,
  config = EclipseStoreRepositoryConfig(name = "timetables"),
  idExtractor = { it.matriculationNumber }
)
