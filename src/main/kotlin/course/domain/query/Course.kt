package io.holixon.example.university.course.domain.query

import java.time.LocalDate

/**
 * Represents a course.
 */
data class Course(
  val id: String,
  val name: String,
  val start: LocalDate,
  val end: LocalDate,
  val maxCapacity: Int
)
