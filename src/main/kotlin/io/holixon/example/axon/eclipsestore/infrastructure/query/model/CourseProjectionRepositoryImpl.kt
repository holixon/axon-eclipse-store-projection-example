package io.holixon.example.axon.eclipsestore.infrastructure.query.model

import io.holixon.example.axon.eclipsestore.adapter.out.query.impl.CourseProjectionRepository
import io.holixon.example.axon.eclipsestore.domain.query.Course
import io.holixon.example.axon.eclipsestore.infrastructure.EclipseStoreRepositoryConfig
import io.holixon.example.axon.eclipsestore.infrastructure.PersistentMapBasedRepository
import io.holixon.example.axon.eclipsestore.infrastructure.StorageRoot

class CourseProjectionRepositoryImpl(
  storageRoot: StorageRoot,
  processingGroupName: String
) : CourseProjectionRepository,
  PersistentMapBasedRepository<String, Course>(
    storageRoot = storageRoot,
    config = EclipseStoreRepositoryConfig(name = processingGroupName),
    idExtractor = { it.id }
  )
