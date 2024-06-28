package io.holixon.example.university.course.infrastructure.adapter.out.query.impl

import io.holixon.axon.eclipsestore.repository.ReadOnlyRepository
import io.holixon.example.university.course.domain.query.Course

/**
 * Course read only repository.
 */
interface CourseProjectionRepository : ReadOnlyRepository<String, Course>
