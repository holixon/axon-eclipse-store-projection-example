package io.holixon.example.university.student.domain.command

/**
 * Course is full.
 */
class CourseFull(courseName: String, maxStudents: Int)
  : IllegalArgumentException("Maximum number of students of $maxStudents in course $courseName is reached.")
