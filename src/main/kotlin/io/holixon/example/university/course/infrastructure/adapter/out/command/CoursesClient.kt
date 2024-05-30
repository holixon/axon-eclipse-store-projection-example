package io.holixon.example.university.course.infrastructure.adapter.out.command

import io.holixon.example.university.course.application.port.out.CoursesCommandOutPort
import io.holixon.example.university.course.application.port.out.ChangeCourseCapacityCommand
import io.holixon.example.university.course.application.port.out.CreateCourseCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class CoursesClient(
  private val commandGateway: CommandGateway
) : CoursesCommandOutPort {
  override fun createCourse(command: CreateCourseCommand): CompletableFuture<Void> = commandGateway.send(command)
  override fun modifyCourse(command: ChangeCourseCapacityCommand): CompletableFuture<Void> = commandGateway.send(command)
}
