package io.holixon.example.axon.eclipsestore.application.port.`in`

import io.holixon.example.axon.eclipsestore.domain.query.Course

interface RetrieveCoursesInPort {
  fun getCourseById(id: String): Course?
  fun getAllCourses(): List<Course>
}
