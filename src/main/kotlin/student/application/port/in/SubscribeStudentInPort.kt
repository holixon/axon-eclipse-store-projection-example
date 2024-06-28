package io.holixon.example.university.student.application.port.`in`

import java.time.LocalDate
import java.util.concurrent.CompletableFuture

/**
 * (Internal) In port to subscribe a student to a course.
 */
interface SubscribeStudentInPort {
  fun subscribe(courseId: String, matriculationNumber: String, subscriptionDate: LocalDate, courseStart: LocalDate, courseEnd: LocalDate): CompletableFuture<Void>
}
