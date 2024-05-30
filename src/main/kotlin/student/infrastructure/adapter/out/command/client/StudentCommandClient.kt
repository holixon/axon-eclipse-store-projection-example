package io.holixon.example.university.student.infrastructure.adapter.out.command.client

import io.holixon.example.university.student.application.port.out.RegisterStudentCommand
import io.holixon.example.university.student.application.port.out.StudentCommandOutPort
import io.holixon.example.university.student.application.port.out.UnregisterStudentCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class StudentCommandClient(
  val commandGateway: CommandGateway
) : StudentCommandOutPort {
  override fun registerStudent(command: RegisterStudentCommand): CompletableFuture<String> = commandGateway.send(command)
  override fun unregisterStudent(command: UnregisterStudentCommand): CompletableFuture<Void> = commandGateway.send(command)
}
