package io.holixon.example.university.student.infrastructure.adapter.`in`.rest

import io.holixon.example.university.student.application.port.`in`.RegisterStudentInPort
import io.holixon.example.university.student.application.port.`in`.ResetStudentProjectionAdminInPort
import io.holixon.example.university.student.application.port.`in`.RetrieveMatriculationsInPort
import io.holixon.example.university.student.application.port.`in`.UnregisterStudentInPort
import io.holixon.example.university.student.domain.command.Person
import io.holixon.example.university.student.domain.query.Matriculation
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.time.LocalDate

@RestController
@RequestMapping("/students")
class StudentController(
  val registerStudentInPort: RegisterStudentInPort,
  val unregisterStudentInPort: UnregisterStudentInPort,
  val retrieveMatriculationsInPort: RetrieveMatriculationsInPort,
  val resetProjectionAdminInPort: ResetStudentProjectionAdminInPort
) {

  @GetMapping
  fun getAllStudents(): ResponseEntity<List<Matriculation>> {
    return ok(retrieveMatriculationsInPort.findAll())
  }

  @PostMapping
  fun register(@RequestBody dto: RegisterStudentDto): ResponseEntity<Void> {
    val matriculationNumber = registerStudentInPort.registerStudent(dto.person, start = LocalDate.parse(dto.start), end = LocalDate.parse(dto.end)).join()
    return created(URI.create(matriculationNumber)).build()
  }

  @GetMapping("/number/{matriculationNumber}")
  fun getStudentByNumber(@PathVariable matriculationNumber: String): ResponseEntity<MatriculationDto> {
    val dto = retrieveMatriculationsInPort.findByNumber(matriculationNumber)?.toDto()
    return if (dto != null) {
      ok(dto)
    } else {
      notFound().build()
    }
  }

  @DeleteMapping("/number/{matriculationNumber}")
  fun unregister(@PathVariable matriculationNumber: String): ResponseEntity<Void> {
    unregisterStudentInPort.unregisterStudent(matriculationNumber)
    return noContent().build()
  }

  @DeleteMapping
  fun reset(): ResponseEntity<Void> {
    resetProjectionAdminInPort.resetStudentProjection()
    return noContent().build()
  }

  data class MatriculationDto(
    val firstName: String,
    val lastName: String,
    val matriculationNumber: String,
    val start: String,
    val end: String
  )

  data class RegisterStudentDto(
    val person: Person,
    val start: String,
    val end: String
  )

  fun Matriculation.toDto() = MatriculationDto(
    firstName = this.firstName,
    lastName = this.lastName,
    matriculationNumber = this.matriculationNumber,
    start = this.start.toString(),
    end = this.end.toString()
  )
}
