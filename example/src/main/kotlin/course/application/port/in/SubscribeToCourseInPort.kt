package io.holixon.example.university.course.application.port.`in`

import io.holixon.example.university.course.domain.command.CourseAlreadyStarted
import io.holixon.example.university.course.domain.command.CourseFull
import io.holixon.example.university.student.domain.command.UnknownCourse
import java.util.concurrent.CompletableFuture

/**
 * Allows to students to subscribe to course.
 */
interface SubscribeToCourseInPort {

  /**
   * Subscribes a student to a course.
   * @param courseId id of the course.
   * @param matriculationNumber: id of the student.
   */
  @Throws(CourseFull::class, CourseAlreadyStarted::class, UnknownCourse::class)
  fun subscribe(courseId: String, matriculationNumber: String): CompletableFuture<Void>
}
