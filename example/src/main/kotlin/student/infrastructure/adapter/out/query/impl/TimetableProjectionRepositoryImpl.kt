package io.holixon.example.university.student.infrastructure.adapter.out.query.impl

import io.holixon.axon.eclipsestore.repository.EclipseStoreRepositoryConfig
import io.holixon.axon.eclipsestore.repository.PersistentMapBasedRepository
import io.holixon.axon.eclipsestore.root.StorageRoot
import io.holixon.example.university.student.domain.query.Timetable

class TimetableProjectionRepositoryImpl(storageRootSupplier: () -> StorageRoot) :
  TimetableProjectionRepository,
  PersistentMapBasedRepository<String, Timetable>(
    storageRootSupplier = storageRootSupplier,
    config = EclipseStoreRepositoryConfig(name = "timetables"),
    idExtractor = { it.matriculationNumber }
  )
