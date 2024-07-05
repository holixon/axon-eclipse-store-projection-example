package io.holixon.example.university.course.infrastructure.adapter.`in`.rest

import io.holixon.example.university.course.application.port.`in`.RetrieveCoursesInPort
import io.holixon.example.university.course.domain.query.Course
import mu.KLogging
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/courses")
class CourseRetrievalController(
  private val retrieveCoursesInPort: RetrieveCoursesInPort
) {
  companion object : KLogging()

  @GetMapping
  fun getCourses(): ResponseEntity<List<CourseDto>> {
    return ok(retrieveCoursesInPort.getAllCourses().map { it.toDto() })
  }

  @GetMapping("/{id}")
  fun getCourseById(@PathVariable("id") id: String): ResponseEntity<CourseDto> {
    return ok(retrieveCoursesInPort.getCourseById(id)?.toDto())
  }

  data class CourseDto(val id: String, val name: String, val start: String, val end: String, val maxStudents: Int, val currentStudents: Int)

  fun Course.toDto() = CourseDto(
    id = this.id,
    name = this.name,
    start = this.start.toString(),
    end = this.end.toString(),
    maxStudents = this.maxCapacity,
    currentStudents = this.currentStudents
  )
}
