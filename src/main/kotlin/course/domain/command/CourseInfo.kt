package io.holixon.example.university.course.domain.command

import java.time.LocalDate

/**
 * Course information.
 */
data class CourseInfo(
  val name: String,
  val start: LocalDate,
  val end: LocalDate
) {

  fun isAlreadyStarted(checkDate: LocalDate) = start.isBefore(checkDate)
}
