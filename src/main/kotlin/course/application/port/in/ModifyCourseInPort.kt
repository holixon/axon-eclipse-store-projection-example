package io.holixon.example.university.course.application.port.`in`

import java.util.concurrent.CompletableFuture

/**
 * Course modification.
 */
interface ModifyCourseInPort {
  /**
   * Modifies the maximum capacity of the course.
   * @param id course id.
   * @param maxStudents new course capacity.
   */
  fun changeNumberOfStudents(id: String, maxStudents: Int): CompletableFuture<Void>
}
