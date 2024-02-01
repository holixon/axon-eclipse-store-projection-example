package io.holixon.example.axon.eclipsestore.adapter.out.command.model

import io.holixon.example.axon.eclipsestore.adapter.out.command.api.ChangeCourseCapacityCommand
import io.holixon.example.axon.eclipsestore.adapter.out.command.api.CreateCourseCommand
import io.holixon.example.axon.eclipsestore.domain.command.CourseCapacity
import io.holixon.example.axon.eclipsestore.domain.event.CourseCapacityChangedEvent
import io.holixon.example.axon.eclipsestore.domain.event.CourseCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
internal class CourseAggregate {

  @AggregateIdentifier
  private lateinit var id: String
  private lateinit var courseCapacity: CourseCapacity

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

  @EventSourcingHandler
  fun on(event: CourseCreatedEvent) {
    this.id = event.id
    this.courseCapacity = CourseCapacity(maxStudents = event.maxStudents)
  }

  @EventSourcingHandler
  fun on(event: CourseCapacityChangedEvent) {
    this.courseCapacity = this.courseCapacity.changeCapacity(maxStudents = event.maxStudents)
  }
}
