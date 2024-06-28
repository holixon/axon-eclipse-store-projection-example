package io.holixon.example.university.student.application.port.`in`

import java.time.LocalDate
import java.util.concurrent.CompletableFuture

/**
 * (Internal) In-Port to unsubscribe the student from the course.
 */
interface UnsubscribeStudentInPort {
  fun unsubscribe(courseId: String, matriculationNumber: String, unsubscriptionDate: LocalDate): CompletableFuture<Void>
}
