package io.holixon.example.university.student.domain.event

import io.holixon.example.university.student.domain.command.Person
import org.axonframework.serialization.Revision
import java.time.LocalDate
import java.time.Year

@Revision("2")
data class StudentRegisteredEvent(
  val matriculationNumber: String,
  val person: Person,
  val start: LocalDate,
  val end: LocalDate,
)
