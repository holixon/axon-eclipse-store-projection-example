package io.holixon.example.university.student.infrastructure.adapter.`in`.event

import io.holixon.example.university.course.application.port.`in`.UnsubscribeFromCourseInPort
import io.holixon.example.university.course.domain.event.CourseSubscriptionCreatedEvent
import io.holixon.example.university.course.domain.event.CourseSubscriptionRemovedEvent
import io.holixon.example.university.student.application.port.`in`.SubscribeStudentInPort
import io.holixon.example.university.student.application.port.`in`.UnsubscribeStudentInPort
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
class CourseSubscriptionEventAdapter(
  private val subscribeStudentInPort: SubscribeStudentInPort,
  private val unsubscribeStudentInPort: UnsubscribeStudentInPort,
  private val unsubscribeFromCourseInPort: UnsubscribeFromCourseInPort
) {

  @EventHandler
  fun on(event: CourseSubscriptionCreatedEvent) {
    subscribeStudentInPort
      .subscribe(courseId = event.courseId, matriculationNumber = event.matriculationNumber, subscriptionDate = event.subscriptionDate)
      .exceptionally {
        unsubscribeFromCourseInPort.unsubscribe(courseId = event.courseId, matriculationNumber = event.matriculationNumber).join()
      }.join()
  }

  @EventHandler
  fun on(event: CourseSubscriptionRemovedEvent) {
    unsubscribeStudentInPort
      .unsubscribe(courseId = event.courseId, matriculationNumber = event.matriculationNumber, unsubscriptionDate = event.unsubscriptionDate)
      .join()
  }
}
