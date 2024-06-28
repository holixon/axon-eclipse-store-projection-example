package io.holixon.example.university.student.infrastructure.adapter.`in`.rest

import io.holixon.example.university.course.application.port.`in`.CreateCourseInPort
import io.holixon.example.university.course.application.port.`in`.ResetCourseProjectionAdminInPort
import io.holixon.example.university.student.application.port.`in`.ResetStudentProjectionAdminInPort
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/student-admin")
class StudentAdminController(
  private val resetCourseProjectionAdminInPort: ResetStudentProjectionAdminInPort
) {


  @DeleteMapping("/timetable")
  fun resetTimetable(): ResponseEntity<Void> {
    resetCourseProjectionAdminInPort.resetTimetableProjection()
    return noContent().build()
  }

  @DeleteMapping("/student")
  fun resetStudent(): ResponseEntity<Void> {
    resetCourseProjectionAdminInPort.resetStudentProjection()
    return noContent().build()
  }

}
