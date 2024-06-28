package io.holixon.example.university.student.domain.query

import java.time.LocalDate

data class CourseSubscription(
  val courseId: String,
  val courseName: String,
  val maxStudents: Int,
  val start: LocalDate,
  val end: LocalDate
)
