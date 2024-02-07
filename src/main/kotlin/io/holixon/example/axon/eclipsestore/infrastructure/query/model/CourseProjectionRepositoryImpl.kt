package io.holixon.example.axon.eclipsestore.infrastructure.query.model

import io.holixon.example.axon.eclipsestore.adapter.out.query.impl.CourseProjectionRepository
import io.holixon.example.axon.eclipsestore.domain.query.Course
import io.holixon.example.axon.eclipsestore.infrastructure.eclipsestore.EclipseStoreRepositoryConfig
import io.holixon.example.axon.eclipsestore.infrastructure.eclipsestore.PersistentMapBasedRepository
import io.holixon.example.axon.eclipsestore.infrastructure.eclipsestore.StorageRoot

class CourseProjectionRepositoryImpl(
  storageRoot: StorageRoot,
  processingGroupName: String
) : CourseProjectionRepository,
  PersistentMapBasedRepository<String, Course>(
    storageRoot = storageRoot,
    config = EclipseStoreRepositoryConfig(name = processingGroupName),
    idExtractor = { it.id }
  )
