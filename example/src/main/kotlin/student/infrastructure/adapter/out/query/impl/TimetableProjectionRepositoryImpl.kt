package io.holixon.example.university.student.infrastructure.adapter.out.query.impl

import io.holixon.axon.eclipsestore.root.StorageRootSupplier
import io.holixon.axon.eclipsestore.repository.EclipseStoreRepositoryConfig
import io.holixon.axon.eclipsestore.repository.ReadOnlyMapBasedRepository
import io.holixon.example.university.student.domain.query.Timetable

class TimetableProjectionRepositoryImpl(storageRootSupplier: StorageRootSupplier) :
  TimetableProjectionRepository,
  ReadOnlyMapBasedRepository<String, Timetable>(
    storageRootSupplier = storageRootSupplier,
    config = EclipseStoreRepositoryConfig(name = "timetables"),
    idExtractor = { it.matriculationNumber }
  )
