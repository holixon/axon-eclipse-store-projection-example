package io.holixon.example.axon.eclipsestore.adapter.out.query.impl

import io.holixon.example.axon.eclipsestore.adapter.out.query.impl.api.AllCoursesMarker
import io.holixon.example.axon.eclipsestore.adapter.out.query.impl.api.CourseById
import io.holixon.example.axon.eclipsestore.domain.query.Course
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
  fun findCourseById(query: CourseById): Optional<Course> {
    return Optional.ofNullable(courseProjectionRepository.findById(query.id))
  }
}
