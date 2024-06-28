package io.holixon.example.university.student.domain.command

import java.time.LocalDate

data class Registration(
  val range: ClosedRange<LocalDate>,
) {
  fun isDuringRegistration(start: LocalDate, end: LocalDate): Boolean =
    range.contains(start) && range.contains(end)

}
