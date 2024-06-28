package io.holixon.example.university.student.infrastructure.adapter.out.projector

import io.holixon.axon.eclipsestore.repository.EclipseStoreRepositoryConfig
import io.holixon.axon.eclipsestore.repository.PersistentMapBasedRepository
import io.holixon.axon.eclipsestore.root.StorageRoot
import io.holixon.example.university.student.domain.query.Matriculation

class StudentProjectorRepositoryImpl(storageRootSupplier: () -> StorageRoot) : StudentProjectorRepository, PersistentMapBasedRepository<String, Matriculation>(
  storageRootSupplier = storageRootSupplier,
  config = EclipseStoreRepositoryConfig(name = "matriculations"),
  idExtractor = { it.matriculationNumber }
)
