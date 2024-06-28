package io.holixon.example.university.course.domain.event

import org.axonframework.serialization.Revision
import java.time.LocalDate

@Revision("1")
data class CourseSubscriptionRemovedEvent(
  val matriculationNumber: String,
  val courseId: String,
  val unsubscriptionDate: LocalDate
)
