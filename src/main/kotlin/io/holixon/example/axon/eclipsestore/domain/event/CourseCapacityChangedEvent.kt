package io.holixon.example.axon.eclipsestore.domain.event

import org.axonframework.serialization.Revision

@Revision("1")
data class CourseCapacityChangedEvent(
  val id: String,
  val maxStudents: Int,
  val currentStudents: Int
)
