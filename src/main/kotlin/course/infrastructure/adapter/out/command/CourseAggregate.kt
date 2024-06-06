package io.holixon.example.university.course.infrastructure.adapter.out.command

import io.holixon.example.university.course.application.port.out.ChangeCourseCapacityCommand
import io.holixon.example.university.course.application.port.out.CreateCourseCommand
import io.holixon.example.university.course.application.port.out.SubscribeToCourseCommand
import io.holixon.example.university.course.domain.command.*
import io.holixon.example.university.course.domain.event.CourseCapacityChangedEvent
import io.holixon.example.university.course.domain.event.CourseCreatedEvent
import io.holixon.example.university.student.domain.event.CourseSubscriptionCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
internal class CourseAggregate() {

  @AggregateIdentifier
  private lateinit var id: String
  private lateinit var courseCapacity: CourseCapacity
  private lateinit var courseSubscriptions: CourseSubscriptions
  private lateinit var courseInfo: CourseInfo

  @CreationPolicy(value = AggregateCreationPolicy.CREATE_IF_MISSING)
  @CommandHandler
  fun handle(cmd: CreateCourseCommand) {
    AggregateLifecycle.apply(
      CourseCreatedEvent(
        id = cmd.id,
        name = cmd.name,
        maxStudents = cmd.maxStudents,
        start = cmd.start,
        end = cmd.end
      )
    )
  }

  @CommandHandler
  @Throws(IllegalCapacityChange::class)
  fun handle(cmd: ChangeCourseCapacityCommand) {
    val newCapacity = courseCapacity.allowChangeCapacity(cmd.maxStudents).getOrThrow()
    AggregateLifecycle.apply(
      CourseCapacityChangedEvent(
        id = cmd.id,
        maxStudents = newCapacity.maxStudents,
        currentStudents = newCapacity.currentStudents
      )
    )
  }
  @CommandHandler
  fun handle(cmd: SubscribeToCourseCommand) {
    if (this.courseCapacity.isFull()) {
      throw CourseFull(this.courseInfo.name, this.courseCapacity.maxStudents)
    }
    this.courseInfo.ensureNotAlreadyStarted(cmd.subscriptionDate)

    if (this.courseSubscriptions.hasStudent(cmd.matriculationNumber)) {
      throw AlreadySubscribedToCourse(cmd.courseId, cmd.matriculationNumber)
    }
    AggregateLifecycle.apply(
      CourseSubscriptionCreatedEvent(
        matriculationNumber = cmd.matriculationNumber,
        courseId = cmd.courseId
      )
    )
  }

  @EventSourcingHandler
  fun on(event: CourseCreatedEvent) {
    this.id = event.id
    this.courseCapacity = CourseCapacity(maxStudents = event.maxStudents)
    this.courseSubscriptions = CourseSubscriptions()
    this.courseInfo = CourseInfo(name = event.name, start = event.start, end = event.end)
  }

  @EventSourcingHandler
  fun on(event: CourseCapacityChangedEvent) {
    this.courseCapacity = this.courseCapacity.changeCapacity(maxStudents = event.maxStudents)
  }

  @EventSourcingHandler
  fun on(event: CourseSubscriptionCreatedEvent) {
    this.courseSubscriptions.addStudent(matriculationNumber = event.matriculationNumber)
    this.courseCapacity = this.courseCapacity.addStudent()
  }


}
