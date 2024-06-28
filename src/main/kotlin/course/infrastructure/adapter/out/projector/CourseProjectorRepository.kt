package io.holixon.example.university.course.infrastructure.adapter.out.projector

import io.holixon.example.university.course.domain.query.Course
import io.holixon.example.university.course.infrastructure.support.eclipsestore.FullAccessRepository

/**
 * Course repository.
 */
interface CourseProjectorRepository : FullAccessRepository<String, Course>
