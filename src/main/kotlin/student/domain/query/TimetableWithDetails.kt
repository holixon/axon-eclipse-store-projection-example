package io.holixon.example.university.student.domain.query

data class TimetableWithDetails(
  val matriculationNumber: String,
  val courses: List<CourseSubscription> = listOf()
)

