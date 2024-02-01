package io.holixon.example.axon.eclipsestore.domain.query

import java.time.LocalDate

data class Course(
  val id: String,
  val name: String,
  val start: LocalDate,
  val end: LocalDate,
  val maxCapacity: Int
)
