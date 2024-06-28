package io.holixon.example.university.student.application.port.out

import io.holixon.example.university.student.domain.command.Person
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDate
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

  fun addStudentToCourse(command: AddStudentToCourseCommand): CompletableFuture<Void>
  fun removeStudentFromCourse(command: RemoveStudentFromCourseCommand): CompletableFuture<Void>
}

data class AddStudentToCourseCommand(
  @TargetAggregateIdentifier
  val matriculationNumber: String,
  val courseId: String,
  val subscriptionDate: LocalDate,
  val courseStartDate: LocalDate,
  val courseEndDate: LocalDate
)

data class RemoveStudentFromCourseCommand(
  @TargetAggregateIdentifier
  val matriculationNumber: String,
  val courseId: String,
  val unsubscriptionDate: LocalDate
)

data class RegisterStudentCommand(
  @TargetAggregateIdentifier
  val matriculationNumber: String,
  val person: Person,
  val registration: Pair<LocalDate, LocalDate>
)

data class UnregisterStudentCommand(
  @TargetAggregateIdentifier
  val matriculationNumber: String
)
