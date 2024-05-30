package io.holixon.example.university.student.application.usecase

import io.holixon.example.university.student.application.port.`in`.RegisterStudentInPort
import io.holixon.example.university.student.application.port.out.RegisterStudentCommand
import io.holixon.example.university.student.application.port.out.StudentCommandOutPort
import io.holixon.example.university.student.domain.command.Person
import org.springframework.stereotype.Component
import java.time.Year
import java.util.concurrent.CompletableFuture

@Component
class RegisterStudentUseCase(
  val studentCommandOutPort: StudentCommandOutPort,
  val matriculationNumberGenerator: MatriculationNumberGenerator
) : RegisterStudentInPort {
  override fun registerStudent(person: Person, year: Year): CompletableFuture<String> {
    val number = matriculationNumberGenerator.generateMatriculationNumber()
    return studentCommandOutPort.registerStudent(
      RegisterStudentCommand(
        matriculationNumber = number,
        person = person,
        year = year
      )
    )
  }
}
