package io.holixon.example.university.student.application.port.`in`

import io.holixon.example.university.student.domain.command.Person
import java.time.Year
import java.util.concurrent.CompletableFuture

/**
 * Student registration.
 */
interface RegisterStudentInPort {

  /**
   * Registers a new student at the university.
   * @param person personal data
   * @param year registration year
   * @return registration id.
   */
  fun registerStudent(person: Person, year: Year): CompletableFuture<String>
}
