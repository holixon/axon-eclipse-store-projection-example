package io.holixon.example.university.course.infrastructure.adapter.out.command

import io.holixon.example.university.course.application.port.out.*
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class CoursesClient(
  private val commandGateway: CommandGateway
) : CoursesCommandOutPort {
  override fun createCourse(command: CreateCourseCommand): CompletableFuture<Void> = commandGateway.send(command)
  override fun modifyCourse(command: ChangeCourseCapacityCommand): CompletableFuture<Void> = commandGateway.send(command)
  override fun subscribeToCourse(command: SubscribeToCourseCommand): CompletableFuture<Void> = commandGateway.send(command)
  override fun unsubscribeFromCourse(command: UnsubscribeFromCourseCommand): CompletableFuture<Void> = commandGateway.send(command)
}
