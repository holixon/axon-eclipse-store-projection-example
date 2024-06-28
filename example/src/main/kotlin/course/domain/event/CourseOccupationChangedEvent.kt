package io.holixon.example.university.course.domain.event

import org.axonframework.serialization.Revision

@Revision("1")
data class CourseOccupationChangedEvent(
  val courseId: String,
  val currentStudents: Int
)
