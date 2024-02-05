package io.holixon.example.axon.eclipsestore.adapter.out.query.impl

import io.holixon.example.axon.eclipsestore.domain.query.Course

interface CourseProjectionRepository {
  fun findById(id: String): Course?
  fun findAll(): List<Course>
  fun save(value: Course): Course
}
