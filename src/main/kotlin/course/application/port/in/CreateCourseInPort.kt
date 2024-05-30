package io.holixon.example.university.course.application.port.`in`

import java.time.LocalDate
import java.util.concurrent.CompletableFuture

/**
 * Create new course.
 */
interface CreateCourseInPort {
  /**
   * Creates a new course.
   * @param id course id.
   * @param name course name.
   * @param maxStudents maximum course capacity.
   * @param start date of course start
   * @param end date of course end.
   */
  fun createCourse(id: String, name: String, maxStudents: Int, start: LocalDate, end: LocalDate): CompletableFuture<Void>
}
