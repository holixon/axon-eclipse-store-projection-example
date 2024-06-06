package io.holixon.example.university.student.infrastructure.adapter.out.query.impl

import io.holixon.example.university.course.infrastructure.support.eclipsestore.EclipseStoreRepositoryConfig
import io.holixon.example.university.course.infrastructure.support.eclipsestore.PersistentMapBasedRepository
import io.holixon.example.university.course.infrastructure.support.eclipsestore.StorageRoot
import io.holixon.example.university.student.domain.query.Matriculation

class MatriculationProjectionRepositoryImpl(
  storageRootSupplier: () -> StorageRoot
) : MatriculationProjectionRepository, PersistentMapBasedRepository<String, Matriculation>(
  storageRootSupplier = storageRootSupplier,
  config = EclipseStoreRepositoryConfig(name = "matriculations"),
  idExtractor = { it.matriculationNumber }
)
