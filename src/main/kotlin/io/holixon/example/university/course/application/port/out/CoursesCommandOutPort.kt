package io.holixon.example.university.course.application.port.out

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDate
import java.util.concurrent.CompletableFuture

/**
 * Command port to manipulate courses.
 */
interface CoursesCommandOutPort {

  /**
   * Creates a new course.
   * @param command command describing the course.
   */
  fun createCourse(command: CreateCourseCommand): CompletableFuture<Void>

  /**
   * Modifies existing course.
   * @param command command describing modification.
   */
  fun modifyCourse(command: ChangeCourseCapacityCommand): CompletableFuture<Void>
}

/**
 * Describes course creation intent.
 */
data class CreateCourseCommand(
  @TargetAggregateIdentifier
  val id: String,
  val name: String,
  val maxStudents: Int,
  val start: LocalDate,
  val end: LocalDate,
)


/**
 * Describes course capacity modification intent.
 */
data class ChangeCourseCapacityCommand(
  @TargetAggregateIdentifier
  val id: String,
  val maxStudents: Int
)
