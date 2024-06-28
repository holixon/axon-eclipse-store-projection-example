package io.holixon.example.university.student.application.port.`in`

import io.holixon.example.university.student.domain.command.Person
import java.time.LocalDate
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
   * @return matriculation number.
   */
  fun registerStudent(person: Person, start: LocalDate, end: LocalDate): CompletableFuture<String>
}
