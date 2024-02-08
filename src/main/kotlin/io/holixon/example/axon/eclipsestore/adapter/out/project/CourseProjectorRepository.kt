package io.holixon.example.axon.eclipsestore.adapter.out.project

import io.holixon.example.axon.eclipsestore.domain.query.Course

/**
 * Course repository.
 */
interface CourseProjectorRepository {
  fun findById(id: String): Course?
  fun findAll(): List<Course>
  fun save(value: Course): Course
  fun deleteAll()
  fun countAll(): Int
}
