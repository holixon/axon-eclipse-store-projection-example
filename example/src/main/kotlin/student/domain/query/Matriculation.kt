package io.holixon.example.university.student.domain.query

import java.time.LocalDate

/**
 * Represents a student registration.
 */
data class Matriculation(
  val matriculationNumber: String,
  val firstName: String,
  val lastName: String,
  val start: LocalDate,
  val end: LocalDate
)
