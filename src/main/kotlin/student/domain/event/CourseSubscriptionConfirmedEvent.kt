package io.holixon.example.university.student.domain.event

import org.axonframework.serialization.Revision

@Revision("1")
data class CourseSubscriptionConfirmedEvent(
  val matriculationNumber: String,
  val courseId: String
)
