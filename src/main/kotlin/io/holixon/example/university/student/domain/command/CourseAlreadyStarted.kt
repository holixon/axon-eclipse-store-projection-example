package io.holixon.example.university.student.domain.command

import java.time.LocalDate

/**
 * Course has already started.
 */
class CourseAlreadyStarted(courseId: String, startDate: LocalDate)
  : IllegalArgumentException("Course $courseId has already started at $startDate.")
