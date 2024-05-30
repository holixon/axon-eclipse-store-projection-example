package io.holixon.example.university.student.domain.event

import org.axonframework.serialization.Revision
import java.time.LocalDate
import java.time.Year

@Revision("1")
data class StudentUnregisteredEvent(
  val matriculationNumber: String,
)
