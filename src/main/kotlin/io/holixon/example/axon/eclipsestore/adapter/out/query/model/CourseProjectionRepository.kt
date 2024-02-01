package io.holixon.example.axon.eclipsestore.adapter.out.query.model

import io.holixon.example.axon.eclipsestore.domain.query.Course

interface CourseProjectionRepository {
  fun findById(id: String): Course?
  fun findAll(): List<Course>
  fun save(course: Course)
}
