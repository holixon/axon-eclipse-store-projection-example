package io.holixon.example.axon.eclipsestore.adapter.out.project

import io.holixon.example.axon.eclipsestore.domain.query.Course
import io.holixon.example.axon.eclipsestore.infrastructure.eclipsestore.EclipseStoreRepositoryConfig
import io.holixon.example.axon.eclipsestore.infrastructure.eclipsestore.PersistentMapBasedRepository
import io.holixon.example.axon.eclipsestore.infrastructure.eclipsestore.StorageRoot

class CourseProjectorRepositoryImpl(
  storageRootSupplier: () -> StorageRoot
) : CourseProjectorRepository,
  PersistentMapBasedRepository<String, Course>(
    storageRootSupplier = storageRootSupplier,
    config = EclipseStoreRepositoryConfig(name = "courses"),
    idExtractor = { it.id }
  )
