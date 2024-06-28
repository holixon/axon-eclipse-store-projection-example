package io.holixon.example.university.student.infrastructure.adapter.`in`.rest

import io.holixon.example.university.course.application.port.`in`.CreateCourseInPort
import io.holixon.example.university.course.application.port.`in`.ResetCourseProjectionAdminInPort
import io.holixon.example.university.student.application.port.`in`.RegisterStudentInPort
import io.holixon.example.university.student.application.port.`in`.ResetStudentProjectionAdminInPort
import io.holixon.example.university.student.domain.command.Person
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
  private val resetCourseProjectionAdminInPort: ResetStudentProjectionAdminInPort,
  private val registerStudentInPort: RegisterStudentInPort
) {

  @PostMapping("/students")
  fun createStudents() {
    registerStudentInPort.registerStudent(
      person = Person(firstName = "Kermit", lastName = "The Frog", birthPlace = "Los Alamos", birthday = LocalDate.parse("1984-01-02")),
      start = LocalDate.parse("2028-02-01"),
      end = LocalDate.parse("2028-07-15")
    )
    registerStudentInPort.registerStudent(
      person = Person(firstName = "Piggy", lastName = "Miss", birthPlace = "Los Angeles", birthday = LocalDate.parse("1987-05-15")),
      start = LocalDate.parse("2028-09-01"),
      end = LocalDate.parse("2029-01-31")
    )
  }


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
