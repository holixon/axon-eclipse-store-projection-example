package io.holixon.example.university.student.domain.command

/**
 * Course is unknown.
 */
class UnknownCourse(courseId: String)
  : IllegalArgumentException("Course with $courseId doesn't exist.")
