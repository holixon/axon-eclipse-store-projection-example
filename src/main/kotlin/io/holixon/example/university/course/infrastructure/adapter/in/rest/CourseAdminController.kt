package io.holixon.example.university.course.infrastructure.adapter.`in`.rest

import io.holixon.example.university.course.application.port.`in`.CreateCourseInPort
import io.holixon.example.university.course.application.port.`in`.ResetProjectionAdminInPort
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/course-admin")
class CourseAdminController(
  private val createCourseInPort: CreateCourseInPort,
  private val resetProjectionAdminInPort: ResetProjectionAdminInPort
) {

  @PostMapping
  fun createCourses(): ResponseEntity<Void> {
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Math I", 25, LocalDate.parse("2024-02-01"), LocalDate.parse("2024-07-15"))
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Physics I", 33, LocalDate.parse("2024-02-01"), LocalDate.parse("2024-07-15"))
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Chemistry", 19, LocalDate.parse("2024-02-01"), LocalDate.parse("2024-07-15"))
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Thermodynamics", 19, LocalDate.parse("2024-02-01"), LocalDate.parse("2024-07-15"))

    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Math II", 25, LocalDate.parse("2024-09-01"), LocalDate.parse("2025-01-31"))
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Physics II", 33, LocalDate.parse("2024-09-01"), LocalDate.parse("2025-01-31"))
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Electronics I", 27, LocalDate.parse("2024-09-01"), LocalDate.parse("2025-01-31"))
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Optics I", 19, LocalDate.parse("2024-09-01"), LocalDate.parse("2025-01-31"))

    return noContent().build()
  }

  @DeleteMapping
  fun reset(): ResponseEntity<Void> {
    resetProjectionAdminInPort.resetCourseProjection()
    return noContent().build()
  }

}
