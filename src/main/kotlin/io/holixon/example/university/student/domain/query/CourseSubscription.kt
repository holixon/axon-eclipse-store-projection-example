package io.holixon.example.university.student.domain.query

/**
 * Represents a subscription to a course.
 */
data class CourseSubscription(
  val courseId: String,
  val matriculationId: String
)
