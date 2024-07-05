package io.holixon.example.university.course.infrastructure.adapter.`in`.rest

import io.holixon.example.university.course.application.port.`in`.CreateCourseInPort
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/course-admin/season")
class CourseAdminController(
  private val createCourseInPort: CreateCourseInPort,
) {

  @PostMapping
  fun createCourses(): ResponseEntity<Void> {

    val startOfSeason0 = LocalDate.parse("2024-02-01")
    val endOfSeason0 = LocalDate.parse("2024-07-15")
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Math I", 25, startOfSeason0, endOfSeason0)
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Physics I", 33, startOfSeason0, endOfSeason0)
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Chemistry", 19, startOfSeason0, endOfSeason0)
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Thermodynamics", 19, startOfSeason0, endOfSeason0)
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Nuclear Power I", 1, startOfSeason0, endOfSeason0)

    val startOfSeason1 = LocalDate.parse("2028-02-01")
    val endOfSeason1 = LocalDate.parse("2028-07-15")
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Math I", 25, startOfSeason1, endOfSeason1)
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Physics I", 33, startOfSeason1, endOfSeason1)
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Chemistry", 19, startOfSeason1, endOfSeason1)
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Thermodynamics", 19, startOfSeason1, endOfSeason1)
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Nuclear Power I", 1, startOfSeason1, endOfSeason1)

    val startOfSeason2 = LocalDate.parse("2028-09-01")
    val endOfSeason2 = LocalDate.parse("2029-01-31")
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Math II", 25, startOfSeason2, endOfSeason2)
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Physics II", 33, startOfSeason2, endOfSeason2)
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Electronics I", 27, startOfSeason2, endOfSeason2)
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Optics I", 19, startOfSeason2, endOfSeason2)
    createCourseInPort.createCourse(UUID.randomUUID().toString(), "Nuclear Power II", 1, startOfSeason2, endOfSeason2)

    return noContent().build()
  }

}
