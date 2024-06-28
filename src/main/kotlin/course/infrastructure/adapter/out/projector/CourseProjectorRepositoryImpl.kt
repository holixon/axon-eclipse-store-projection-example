package io.holixon.example.university.course.infrastructure.adapter.out.projector

import io.holixon.example.university.course.domain.query.Course
import io.holixon.example.university.course.infrastructure.support.eclipsestore.EclipseStoreRepositoryConfig
import io.holixon.example.university.course.infrastructure.support.eclipsestore.PersistentMapBasedRepository
import io.holixon.example.university.course.infrastructure.support.eclipsestore.StorageRoot

class CourseProjectorRepositoryImpl(storageRootSupplier: () -> StorageRoot) :
  CourseProjectorRepository,
  PersistentMapBasedRepository<String, Course>(
    storageRootSupplier = storageRootSupplier,
    config = EclipseStoreRepositoryConfig(name = "courses"),
    idExtractor = { it.id }
  )
