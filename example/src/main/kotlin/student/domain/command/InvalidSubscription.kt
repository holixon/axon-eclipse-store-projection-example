package io.holixon.example.university.student.domain.command

import java.time.LocalDate

class InvalidSubscription(courseId: String, courseStart: LocalDate, courseEnd: LocalDate, registration: Registration)
  : IllegalArgumentException("Invalid subscription to $courseId, which runs between $courseStart and $courseEnd, but the student is registered between ${registration.range.start} and ${registration.range.endInclusive}")
