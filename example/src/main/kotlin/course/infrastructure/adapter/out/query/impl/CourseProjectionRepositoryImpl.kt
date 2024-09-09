package io.holixon.example.university.course.infrastructure.adapter.out.query.impl

import io.holixon.axon.eclipsestore.repository.EclipseStoreRepositoryConfig
import io.holixon.axon.eclipsestore.repository.ReadOnlyMapBasedRepository
import io.holixon.axon.eclipsestore.root.StorageRootSupplier
import io.holixon.example.university.course.domain.query.Course

class CourseProjectionRepositoryImpl(storageRootSupplier: StorageRootSupplier) :
  CourseProjectionRepository,
  ReadOnlyMapBasedRepository<String, Course>(
    storageRootSupplier = storageRootSupplier,
    config = EclipseStoreRepositoryConfig(name = "courses"),
    idExtractor = { it.id }
  )
