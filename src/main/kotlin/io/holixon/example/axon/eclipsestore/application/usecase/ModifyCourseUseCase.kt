package io.holixon.example.axon.eclipsestore.application.usecase

import io.holixon.example.axon.eclipsestore.adapter.out.command.api.ChangeCourseCapacityCommand
import io.holixon.example.axon.eclipsestore.application.port.`in`.ModifyCourseInPort
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class ModifyCourseUseCase(
  val commandGateway: CommandGateway
) : ModifyCourseInPort {
  override fun changeNumberOfStudents(id: String, maxStudents: Int): CompletableFuture<Void> =
    commandGateway.send(
      ChangeCourseCapacityCommand(
        id = id,
        maxStudents = maxStudents
      )
    )
}
