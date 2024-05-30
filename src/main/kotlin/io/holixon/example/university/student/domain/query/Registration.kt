package io.holixon.example.university.student.domain.query

import java.time.Year

/**
 * Represents a registration.
 */
data class Registration(
  val registrationNumber: String,
  val firstName: String,
  val lastName: String,
  val registrationYear: Year
)
