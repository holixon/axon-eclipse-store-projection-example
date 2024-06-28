package io.holixon.example.university.student.domain.event

import org.axonframework.serialization.Revision
import java.time.LocalDate

@Revision("1")
data class StudentJoinedCourseEvent(
  val matriculationNumber: String,
  val courseId: String,
  val subscriptionDate: LocalDate
)
