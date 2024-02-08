package io.holixon.example.axon.eclipsestore.adapter.out.query.impl

import io.holixon.example.axon.eclipsestore.domain.query.Course

/**
 * Course read only repository.
 */
interface CourseProjectionRepository {
  fun findById(id: String): Course?
  fun findAll(): List<Course>
}
