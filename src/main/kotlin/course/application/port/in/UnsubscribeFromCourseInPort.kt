package io.holixon.example.university.course.application.port.`in`

import io.holixon.example.university.course.domain.command.CourseAlreadyStarted
import io.holixon.example.university.course.domain.command.CourseFull
import io.holixon.example.university.student.domain.command.UnknownCourse
import java.util.concurrent.CompletableFuture

/**
 * Allows to students to unsubscribe from course.
 */
interface UnsubscribeFromCourseInPort {

  /**
   * Subscribes a student to a course.
   * @param courseId id of the course.
   * @param matriculationNumber: id of the student.
   */
  @Throws(CourseAlreadyStarted::class, UnknownCourse::class)
  fun unsubscribe(courseId: String, matriculationNumber: String): CompletableFuture<Void>
}
