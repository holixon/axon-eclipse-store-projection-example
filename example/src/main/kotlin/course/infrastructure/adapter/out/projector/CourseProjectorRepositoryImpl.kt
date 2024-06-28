package io.holixon.example.university.course.infrastructure.adapter.out.projector

import io.holixon.axon.eclipsestore.repository.EclipseStoreRepositoryConfig
import io.holixon.axon.eclipsestore.repository.PersistentMapBasedRepository
import io.holixon.axon.eclipsestore.root.StorageRoot
import io.holixon.example.university.course.domain.query.Course

class CourseProjectorRepositoryImpl(storageRootSupplier: () -> StorageRoot) :
  CourseProjectorRepository,
  PersistentMapBasedRepository<String, Course>(
    storageRootSupplier = storageRootSupplier,
    config = EclipseStoreRepositoryConfig(name = "courses"),
    idExtractor = { it.id }
  )