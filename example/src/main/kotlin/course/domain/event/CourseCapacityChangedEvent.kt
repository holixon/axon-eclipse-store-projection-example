package io.holixon.example.university.course.domain.event

import org.axonframework.serialization.Revision

@Revision("1")
data class CourseCapacityChangedEvent(
  val courseId: String,
  val maxStudents: Int,
  val currentStudents: Int
)
