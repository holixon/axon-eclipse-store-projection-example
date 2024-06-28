package io.holixon.example.university.student.infrastructure.adapter.out.query.impl

import io.holixon.axon.eclipsestore.repository.EclipseStoreRepositoryConfig
import io.holixon.axon.eclipsestore.repository.PersistentMapBasedRepository
import io.holixon.axon.eclipsestore.root.StorageRoot
import io.holixon.example.university.student.domain.query.Matriculation

class MatriculationProjectionRepositoryImpl(
  storageRootSupplier: () -> StorageRoot
) : MatriculationProjectionRepository,
  PersistentMapBasedRepository<String, Matriculation>(
    storageRootSupplier = storageRootSupplier,
    config = EclipseStoreRepositoryConfig(name = "matriculations"),
    idExtractor = { it.matriculationNumber }
  )
