package io.holixon.example.university.student.infrastructure.adapter.`in`.event

import io.holixon.example.university.course.application.port.`in`.RetrieveCoursesInPort
import io.holixon.example.university.course.application.port.`in`.UnsubscribeFromCourseInPort
import io.holixon.example.university.course.domain.event.CourseSubscriptionCreatedEvent
import io.holixon.example.university.course.domain.event.CourseSubscriptionRemovedEvent
import io.holixon.example.university.student.application.port.`in`.SubscribeStudentInPort
import io.holixon.example.university.student.application.port.`in`.UnsubscribeStudentInPort
import io.holixon.example.university.student.infrastructure.adapter.`in`.event.CourseSubscriptionProcessManager.Companion.GROUP
import jakarta.inject.Inject
import mu.KLogging
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.modelling.saga.EndSaga
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.springframework.stereotype.Component

@Component
@ProcessingGroup(GROUP)
@Saga
class CourseSubscriptionProcessManager {

  companion object: KLogging() {
    const val GROUP = "course-subscription";
  }

  @Inject
  @Transient
  private lateinit var subscribeStudentInPort: SubscribeStudentInPort
  @Inject
  @Transient
  private lateinit var unsubscribeStudentInPort: UnsubscribeStudentInPort
  @Inject
  @Transient
  private lateinit var unsubscribeFromCourseInPort: UnsubscribeFromCourseInPort
  @Inject
  @Transient
  private lateinit var retrieveCoursesInPort: RetrieveCoursesInPort


  @StartSaga
  @SagaEventHandler(associationProperty = "matriculationNumber")
  fun on(event: CourseSubscriptionCreatedEvent) {
    retrieveCoursesInPort.getCourseById(event.courseId)?.let { course ->
      subscribeStudentInPort
        .subscribe(
          courseId = event.courseId,
          matriculationNumber = event.matriculationNumber,
          subscriptionDate = event.subscriptionDate,
          courseStart = course.start,
          courseEnd = course.end
        )
        .exceptionally { e1 ->
          logger.error(e1) { "Could not subscribe: ${e1.message}" }
          unsubscribeFromCourseInPort
            .unsubscribe(courseId = event.courseId, matriculationNumber = event.matriculationNumber)
            .exceptionally { e2 ->
              logger.error{ "Could not unsubscribe: ${e2.message}" }
              null
            }
            .join()
        }.join()
    }

    SagaLifecycle.end()
  }

  @StartSaga
  @SagaEventHandler(associationProperty = "matriculationNumber")
  fun on(event: CourseSubscriptionRemovedEvent) {
    unsubscribeStudentInPort
      .unsubscribe(courseId = event.courseId, matriculationNumber = event.matriculationNumber, unsubscriptionDate = event.unsubscriptionDate)
      .join()
    SagaLifecycle.end()
  }
}
