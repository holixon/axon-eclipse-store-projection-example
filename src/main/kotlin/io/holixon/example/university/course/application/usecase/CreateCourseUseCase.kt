package io.holixon.example.university.course.application.usecase

import io.holixon.example.university.course.infrastructure.adapter.out.command.api.CreateCourseCommand
import io.holixon.example.university.course.application.port.`in`.CreateCourseInPort
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.concurrent.CompletableFuture

@Component
class CreateCourseUseCase(
  val commandGateway: CommandGateway
) : CreateCourseInPort {

  override fun createCourse(id: String, name: String, maxStudents: Int, start: LocalDate, end: LocalDate): CompletableFuture<Void> =
    commandGateway.send(CreateCourseCommand(
      id = id,
      name = name,
      maxStudents = maxStudents,
      start = start,
      end = end
    ))

}
