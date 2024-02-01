package io.holixon.example.axon.eclipsestore.application.usecase

import io.holixon.example.axon.eclipsestore.adapter.out.command.api.CreateCourseCommand
import io.holixon.example.axon.eclipsestore.application.port.`in`.CreateCourseInPort
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
