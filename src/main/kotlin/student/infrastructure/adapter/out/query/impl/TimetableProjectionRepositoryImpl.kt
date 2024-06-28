package io.holixon.example.university.student.infrastructure.adapter.out.query.impl

import io.holixon.example.university.course.infrastructure.support.eclipsestore.EclipseStoreRepositoryConfig
import io.holixon.example.university.course.infrastructure.support.eclipsestore.PersistentMapBasedRepository
import io.holixon.example.university.course.infrastructure.support.eclipsestore.ReadOnlyMapBasedRepository
import io.holixon.example.university.course.infrastructure.support.eclipsestore.StorageRoot
import io.holixon.example.university.student.domain.query.Matriculation
import io.holixon.example.university.student.domain.query.Timetable

class TimetableProjectionRepositoryImpl(storageRootSupplier: () -> StorageRoot) : TimetableProjectionRepository, ReadOnlyMapBasedRepository<String, Timetable>(
  storageRootSupplier = storageRootSupplier,
  config = EclipseStoreRepositoryConfig(name = "timetables"),
  idExtractor = { it.matriculationNumber }
)
