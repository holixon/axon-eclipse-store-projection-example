package io.holixon.example.university.student.domain.event

import org.axonframework.serialization.Revision

@Revision("1")
data class SubscribedToCourseEvent(
  val matriculationNumber: String,
  val courseId: String
)
