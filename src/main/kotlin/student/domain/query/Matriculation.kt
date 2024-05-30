package io.holixon.example.university.student.domain.query

import java.time.Year

/**
 * Represents a student registration.
 */
data class Matriculation(
  val matriculationNumber: String,
  val firstName: String,
  val lastName: String,
  val year: Year
)
