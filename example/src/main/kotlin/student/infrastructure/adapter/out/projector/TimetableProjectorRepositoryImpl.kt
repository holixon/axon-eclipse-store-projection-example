package io.holixon.example.university.student.infrastructure.adapter.out.projector

import io.holixon.axon.eclipsestore.root.StorageRootSupplier
import io.holixon.axon.eclipsestore.repository.EclipseStoreRepositoryConfig
import io.holixon.axon.eclipsestore.repository.PersistentMapBasedRepository
import io.holixon.example.university.student.domain.query.Timetable

class TimetableProjectorRepositoryImpl(storageRootSupplier: StorageRootSupplier) :
  TimetableProjectorRepository,
  PersistentMapBasedRepository<String, Timetable>(
    storageRootSupplier = storageRootSupplier,
    config = EclipseStoreRepositoryConfig(name = "timetables"),
    idExtractor = { it.matriculationNumber }
  )
