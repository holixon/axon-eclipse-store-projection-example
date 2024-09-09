package io.holixon.example.university.student.infrastructure.adapter.out.projector

import io.holixon.axon.eclipsestore.root.StorageRootSupplier
import io.holixon.axon.eclipsestore.repository.EclipseStoreRepositoryConfig
import io.holixon.axon.eclipsestore.repository.PersistentMapBasedRepository
import io.holixon.example.university.student.domain.query.Matriculation

class StudentProjectorRepositoryImpl(storageRootSupplier: StorageRootSupplier) : StudentProjectorRepository,
  PersistentMapBasedRepository<String, Matriculation>(
  storageRootSupplier = storageRootSupplier,
  config = EclipseStoreRepositoryConfig(name = "matriculations"),
  idExtractor = { it.matriculationNumber }
)
