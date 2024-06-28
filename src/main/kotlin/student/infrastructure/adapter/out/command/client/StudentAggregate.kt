package io.holixon.example.university.student.infrastructure.adapter.out.command.client

import io.holixon.example.university.student.application.port.out.RegisterStudentCommand
import io.holixon.example.university.student.application.port.out.AddStudentToCourseCommand
import io.holixon.example.university.student.application.port.out.UnregisterStudentCommand
import io.holixon.example.university.student.application.port.out.RemoveStudentFromCourseCommand
import io.holixon.example.university.student.domain.command.Person
import io.holixon.example.university.student.domain.event.StudentJoinedCourseEvent
import io.holixon.example.university.student.domain.event.StudentLeftCourseEvent
import io.holixon.example.university.student.domain.event.StudentRegisteredEvent
import io.holixon.example.university.student.domain.event.StudentUnregisteredEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
internal class StudentAggregate() {

  @AggregateIdentifier
  private lateinit var matriculationNumber: String
  private lateinit var person: Person

  @CreationPolicy(value = AggregateCreationPolicy.CREATE_IF_MISSING)
  @CommandHandler
  fun handle(cmd: RegisterStudentCommand) : String {
    AggregateLifecycle.apply(
      StudentRegisteredEvent(
        matriculationNumber = cmd.matriculationNumber,
        person = cmd.person,
        year = cmd.year
      )
    )
    return cmd.matriculationNumber
  }

  @CommandHandler
  fun handle(cmd: UnregisterStudentCommand) {
    AggregateLifecycle.apply(
      StudentUnregisteredEvent(
        matriculationNumber = cmd.matriculationNumber
      )
    )
    AggregateLifecycle.markDeleted()
  }

  @CommandHandler
  fun handle(cmd: AddStudentToCourseCommand) {
    AggregateLifecycle.apply(
      StudentJoinedCourseEvent(
        matriculationNumber = cmd.matriculationNumber,
        courseId = cmd.courseId,
        subscriptionDate = cmd.subscriptionDate
      )
    )
  }

  @CommandHandler
  fun handle(cmd: RemoveStudentFromCourseCommand) {
    AggregateLifecycle.apply(
      StudentLeftCourseEvent(
        matriculationNumber = cmd.matriculationNumber,
        courseId = cmd.courseId,
        unsubscriptionDate = cmd.unsubscriptionDate
      )
    )
  }

  @EventSourcingHandler
  fun on(evt: StudentRegisteredEvent) {
    this.matriculationNumber = evt.matriculationNumber
    this.person = evt.person
  }
}
