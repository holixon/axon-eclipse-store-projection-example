package io.holixon.example.university.student.domain.query

/**
 * Read side model of a student timetable including course details.
 */
data class TimetableWithDetails(
  val matriculationNumber: String,
  val courses: List<CourseSubscription> = listOf()
)

