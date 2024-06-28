package io.holixon.example.university.student.infrastructure.adapter.`in`.rest

import io.holixon.example.university.student.application.port.`in`.RetrieveStudentTimetableInPort
import io.holixon.example.university.student.domain.query.CourseSubscription
import io.holixon.example.university.student.domain.query.TimetableWithDetails
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/timetables")
class TimetableController(
  private val retrieveStudentTimetableInPort: RetrieveStudentTimetableInPort
) {

  @GetMapping("/{matriculationNumber}")
  fun getTimetable(@PathVariable("matriculationNumber") matriculationNumber: String): ResponseEntity<TimeTableDto> {
    return ok(retrieveStudentTimetableInPort.getTimetable(matriculationNumber)?.toDto())
  }

  fun TimetableWithDetails.toDto() = TimeTableDto(
    matriculationNumber = this.matriculationNumber,
    courses = this.courses.map { it.toDto() }
  )

  fun CourseSubscription.toDto() = CourseDto(
    courseName = this.courseName,
    start = this.start,
    end = this.end,
    maxStudents = this.maxStudents
  )

  data class TimeTableDto(
    val matriculationNumber: String,
    val courses: List<CourseDto>
  )

  data class CourseDto(
    val courseName: String,
    val start: LocalDate,
    val end: LocalDate,
    val maxStudents: Int
  )
}
