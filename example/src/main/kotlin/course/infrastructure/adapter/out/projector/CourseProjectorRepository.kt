package io.holixon.example.university.course.infrastructure.adapter.out.projector

import io.holixon.axon.eclipsestore.repository.FullAccessRepository
import io.holixon.example.university.course.domain.query.Course

/**
 * Course repository.
 */
interface CourseProjectorRepository : FullAccessRepository<String, Course>
