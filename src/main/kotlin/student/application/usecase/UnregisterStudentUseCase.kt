package io.holixon.example.university.student.application.usecase

import io.holixon.example.university.student.application.port.`in`.UnregisterStudentInPort
import io.holixon.example.university.student.application.port.out.StudentCommandOutPort
import io.holixon.example.university.student.application.port.out.UnregisterStudentCommand
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class UnregisterStudentUseCase(
  private val studentCommandOutPort: StudentCommandOutPort
): UnregisterStudentInPort {
  override fun unregisterStudent(matriculationNumber: String): CompletableFuture<Void>
  = studentCommandOutPort.unregisterStudent(
    UnregisterStudentCommand(matriculationNumber)
  )
}
