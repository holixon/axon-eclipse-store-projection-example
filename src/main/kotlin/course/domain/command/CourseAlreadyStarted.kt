package io.holixon.example.university.course.domain.command

import java.time.LocalDate

/**
 * Course has already started.
 */
class CourseAlreadyStarted(courseName: String, startDate: LocalDate)
  : IllegalArgumentException("Course $courseName has already started at $startDate.")
