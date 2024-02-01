package io.holixon.example.axon.eclipsestore.domain.event

import org.axonframework.serialization.Revision
import java.time.LocalDate

@Revision("1")
data class CourseCreatedEvent(
  val id: String,
  val name: String,
  val maxStudents: Int,
  val start: LocalDate,
  val end: LocalDate,
)
