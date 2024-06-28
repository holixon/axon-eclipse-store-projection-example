package io.holixon.example.university.student.application.port.`in`

import java.util.concurrent.CompletableFuture

/**
 * Student un-registration.
 */
interface UnregisterStudentInPort {

  /**
   * Unregisters a new student at the university.
   * @param matriculationNumber registration number of the student.
   */
  fun unregisterStudent(matriculationNumber: String): CompletableFuture<Void>
}
