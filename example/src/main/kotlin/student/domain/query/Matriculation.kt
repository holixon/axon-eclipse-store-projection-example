package io.holixon.example.university.student.domain.query

import java.time.LocalDate

/**
 * Read side model to represents a student registration.
 */
data class Matriculation(
  val matriculationNumber: String,
  val firstName: String,
  val lastName: String,
  val start: LocalDate,
  val end: LocalDate
)
