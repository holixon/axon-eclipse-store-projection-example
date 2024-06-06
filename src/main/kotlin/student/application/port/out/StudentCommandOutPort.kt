package io.holixon.example.university.student.application.port.out

import io.holixon.example.university.student.domain.command.Person
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDate
import java.time.Year
import java.util.concurrent.CompletableFuture

/**
 * Operations to subscribe to and unsubscribe from university.
 */
interface StudentCommandOutPort {

  /**
   * Registers new student.
   */
  fun registerStudent(command: RegisterStudentCommand): CompletableFuture<String>

  /**
   * Unregisters an existing student.
   */
  fun unregisterStudent(command: UnregisterStudentCommand): CompletableFuture<Void>

}

data class RegisterStudentCommand(
  @TargetAggregateIdentifier
  val matriculationNumber: String,
  val person: Person,
  val year: Year
)

data class UnregisterStudentCommand(
  @TargetAggregateIdentifier
  val matriculationNumber: String
)
