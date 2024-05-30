package io.holixon.example.university.student.domain.event

import io.holixon.example.university.student.domain.command.Person
import org.axonframework.serialization.Revision
import java.time.Year

@Revision("1")
data class StudentRegisteredEvent(
  val matriculationNumber: String,
  val person: Person,
  val year: Year
)
