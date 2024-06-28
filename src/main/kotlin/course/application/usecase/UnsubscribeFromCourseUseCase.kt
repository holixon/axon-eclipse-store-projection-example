package io.holixon.example.university.course.application.usecase

import io.holixon.example.university.course.application.port.`in`.UnsubscribeFromCourseInPort
import io.holixon.example.university.course.application.port.out.CoursesCommandOutPort
import io.holixon.example.university.course.application.port.out.UnsubscribeFromCourseCommand
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.LocalDate
import java.util.concurrent.CompletableFuture

@Component
class UnsubscribeFromCourseUseCase(
  private val courseCommandOutPort: CoursesCommandOutPort,
  private val systemClock: Clock
) : UnsubscribeFromCourseInPort {
  override fun unsubscribe(courseId: String, matriculationNumber: String): CompletableFuture<Void> {
    return courseCommandOutPort.unsubscribeFromCourse(
      UnsubscribeFromCourseCommand(courseId = courseId, matriculationNumber = matriculationNumber, unsubscriptionDate = LocalDate.now(systemClock))
    )
  }
}
