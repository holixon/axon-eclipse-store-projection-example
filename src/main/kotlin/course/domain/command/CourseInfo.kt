package io.holixon.example.university.course.domain.command

import java.time.LocalDate
import kotlin.jvm.Throws

/**
 * Course information.
 */
data class CourseInfo(
  val name: String,
  val start: LocalDate,
  val end: LocalDate
) {
  @Throws(CourseAlreadyStarted::class)
  fun ensureNotAlreadyStarted(subscriptionDate: LocalDate) {
    if (start.isBefore(subscriptionDate)) {
      throw CourseAlreadyStarted(courseName = name, startDate = start)
    }
  }
}
