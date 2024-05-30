package io.holixon.example.university.student.infrastructure.adapter.out.projector

import io.holixon.example.university.course.infrastructure.support.eclipsestore.EclipseStoreRepositoryConfig
import io.holixon.example.university.course.infrastructure.support.eclipsestore.PersistentMapBasedRepository
import io.holixon.example.university.course.infrastructure.support.eclipsestore.StorageRoot
import io.holixon.example.university.student.domain.query.Matriculation

class StudentProjectorRepositoryImpl(storageRootSupplier: () -> StorageRoot) : StudentProjectorRepository, PersistentMapBasedRepository<String, Matriculation>(
  storageRootSupplier = storageRootSupplier,
  config = EclipseStoreRepositoryConfig(name = "matriculations"),
  idExtractor = { it.matriculationNumber }
)
