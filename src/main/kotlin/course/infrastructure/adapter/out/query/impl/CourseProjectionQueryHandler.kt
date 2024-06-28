package io.holixon.example.university.course.infrastructure.adapter.out.query.impl

import io.holixon.example.university.course.domain.query.Course
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component
import java.util.*

@Component
class CourseProjectionQueryHandler(
  private val courseProjectionRepository: CourseProjectionRepository
) {

  @QueryHandler(queryName = "findCourses")
  fun findCourses(query: AllCoursesMarker): MutableList<Course> {
    return courseProjectionRepository.findAll().toMutableList()
  }

  @QueryHandler(queryName = "findCourseById")
  fun findCourseById(query: CourseById): Course? {
    return courseProjectionRepository.findByIdOrNull(query.id)
  }
}
