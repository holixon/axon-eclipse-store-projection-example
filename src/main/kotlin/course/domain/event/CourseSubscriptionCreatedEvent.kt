package io.holixon.example.university.course.domain.event

import org.axonframework.serialization.Revision
import java.time.LocalDate

@Revision("1")
data class CourseSubscriptionCreatedEvent(
  val matriculationNumber: String,
  val courseId: String,
  val subscriptionDate: LocalDate
)
