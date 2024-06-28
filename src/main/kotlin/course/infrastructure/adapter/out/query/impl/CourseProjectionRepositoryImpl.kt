package io.holixon.example.university.course.infrastructure.adapter.out.query.impl

import io.holixon.example.university.course.domain.query.Course
import io.holixon.example.university.course.infrastructure.support.eclipsestore.EclipseStoreRepositoryConfig
import io.holixon.example.university.course.infrastructure.support.eclipsestore.PersistentMapBasedRepository
import io.holixon.example.university.course.infrastructure.support.eclipsestore.StorageRoot

class CourseProjectionRepositoryImpl(storageRootSupplier: () -> StorageRoot) :
  CourseProjectionRepository,
  PersistentMapBasedRepository<String, Course>(
    storageRootSupplier = storageRootSupplier,
    config = EclipseStoreRepositoryConfig(name = "courses"),
    idExtractor = { it.id }
  )
