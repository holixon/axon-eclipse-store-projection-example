package io.holixon.example.university.student.domain.command

import java.time.LocalDate

/**
 * Represents a person.
 */
data class Person(
  val firstName: String,
  val lastName: String,
  val birthday: LocalDate,
  val birthPlace: String,
)
