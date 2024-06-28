package io.holixon.example.university.course.application.usecase

import io.holixon.example.university.course.application.port.`in`.SubscribeToCourseInPort
import io.holixon.example.university.course.application.port.out.CoursesCommandOutPort
import io.holixon.example.university.course.application.port.out.SubscribeToCourseCommand
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.LocalDate
import java.util.concurrent.CompletableFuture

@Component
class SubscribeToCourseUseCase(
  val courseCommandOutPort: CoursesCommandOutPort,
  val systemClock: Clock
) : SubscribeToCourseInPort {

  override fun subscribe(courseId: String, matriculationNumber: String): CompletableFuture<Void> {
    return courseCommandOutPort.subscribeToCourse(
      SubscribeToCourseCommand(matriculationNumber = matriculationNumber, courseId = courseId, subscriptionDate = LocalDate.now(systemClock))
    )
  }
}
