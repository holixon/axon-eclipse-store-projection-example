package io.holixon.example.university.course.infrastructure.adapter.out.projector

import io.holixon.example.university.course.domain.query.Course

/**
 * Course repository.
 */
interface CourseProjectorRepository {
  fun findById(id: String): Course?
  fun save(value: Course): Course
  fun deleteAll()
  fun countAll(): Int
}
