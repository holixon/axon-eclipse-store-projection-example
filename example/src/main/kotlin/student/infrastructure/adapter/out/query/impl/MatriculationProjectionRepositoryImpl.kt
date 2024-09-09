package io.holixon.example.university.student.infrastructure.adapter.out.query.impl

import io.holixon.axon.eclipsestore.repository.EclipseStoreRepositoryConfig
import io.holixon.axon.eclipsestore.repository.ReadOnlyMapBasedRepository
import io.holixon.axon.eclipsestore.root.StorageRootSupplier
import io.holixon.example.university.student.domain.query.Matriculation

class MatriculationProjectionRepositoryImpl(
  storageRootSupplier: StorageRootSupplier
) : MatriculationProjectionRepository,
  ReadOnlyMapBasedRepository<String, Matriculation>(
    storageRootSupplier = storageRootSupplier,
    config = EclipseStoreRepositoryConfig(name = "matriculations"),
    idExtractor = { it.matriculationNumber }
  )
