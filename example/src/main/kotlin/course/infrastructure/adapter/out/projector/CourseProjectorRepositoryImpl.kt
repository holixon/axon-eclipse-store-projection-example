package io.holixon.example.university.course.infrastructure.adapter.out.projector

import io.holixon.axon.eclipsestore.root.StorageRootSupplier
import io.holixon.axon.eclipsestore.repository.EclipseStoreRepositoryConfig
import io.holixon.axon.eclipsestore.repository.PersistentMapBasedRepository
import io.holixon.example.university.course.domain.query.Course

class CourseProjectorRepositoryImpl(storageRootSupplier: StorageRootSupplier) :
  CourseProjectorRepository,
  PersistentMapBasedRepository<String, Course>(
    storageRootSupplier = storageRootSupplier,
    config = EclipseStoreRepositoryConfig(name = "courses"),
    idExtractor = { it.id }
  )
