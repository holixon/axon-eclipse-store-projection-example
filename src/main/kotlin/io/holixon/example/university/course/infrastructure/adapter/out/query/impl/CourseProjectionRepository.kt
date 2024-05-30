package io.holixon.example.university.course.infrastructure.adapter.out.query.impl

import io.holixon.example.university.course.domain.query.Course

/**
 * Course read only repository.
 */
interface CourseProjectionRepository {
  fun findById(id: String): Course?
  fun findAll(): List<Course>
}
