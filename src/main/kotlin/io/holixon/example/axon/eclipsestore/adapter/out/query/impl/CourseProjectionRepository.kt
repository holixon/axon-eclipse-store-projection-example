package io.holixon.example.axon.eclipsestore.adapter.out.query.impl

import io.holixon.example.axon.eclipsestore.domain.query.Course

/**
 * Course repository.
 */
interface CourseProjectionRepository {
  fun findById(id: String): Course?
  fun findAll(): List<Course>
  fun save(value: Course): Course
  fun deleteAll()
  fun countAll(): Int
}
