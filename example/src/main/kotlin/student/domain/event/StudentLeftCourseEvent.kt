package io.holixon.example.university.student.domain.event

import org.axonframework.serialization.Revision
import java.time.LocalDate

@Revision("1")
data class StudentLeftCourseEvent(
  val matriculationNumber: String,
  val courseId: String,
  val unsubscriptionDate: LocalDate
)
