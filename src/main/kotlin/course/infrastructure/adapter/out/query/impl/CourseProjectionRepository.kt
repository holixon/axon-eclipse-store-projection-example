package io.holixon.example.university.course.infrastructure.adapter.out.query.impl

import io.holixon.example.university.course.domain.query.Course
import io.holixon.example.university.course.infrastructure.support.eclipsestore.ReadOnlyRepository

/**
 * Course read only repository.
 */
interface CourseProjectionRepository : ReadOnlyRepository<String, Course>
