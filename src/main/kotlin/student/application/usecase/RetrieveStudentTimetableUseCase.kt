package io.holixon.example.university.student.application.usecase

import io.holixon.example.university.course.application.port.`in`.RetrieveCoursesInPort
import io.holixon.example.university.course.domain.query.Course
import io.holixon.example.university.student.application.port.`in`.RetrieveStudentTimetableInPort
import io.holixon.example.university.student.application.port.out.TimeTableOutPort
import io.holixon.example.university.student.domain.query.CourseSubscription
import io.holixon.example.university.student.domain.query.TimetableWithDetails
import org.springframework.stereotype.Component

@Component
class RetrieveStudentTimetableUseCase(
  private val retrieveCoursesInPort: RetrieveCoursesInPort,
  private val timeTableOutPort: TimeTableOutPort
) : RetrieveStudentTimetableInPort {

  override fun getTimetable(matriculationNumber: String): TimetableWithDetails? {
    return timeTableOutPort.getTableName(matriculationNumber = matriculationNumber).join().orElse(null)?.let { timetable ->
      TimetableWithDetails(
        matriculationNumber = matriculationNumber,
        courses = timetable.courses
          .mapNotNull { courseId -> retrieveCoursesInPort.getCourseById(courseId) }
          .map { course -> course.toCourseSubscription() }
      )
    }
  }

  fun Course.toCourseSubscription(): CourseSubscription = CourseSubscription(
    courseId = this.id,
    courseName = this.name,
    maxStudents = this.maxCapacity,
    start = this.start,
    end = this.end
  )

}
