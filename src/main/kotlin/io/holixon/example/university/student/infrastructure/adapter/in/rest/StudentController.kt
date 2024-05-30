package io.holixon.example.university.student.infrastructure.adapter.`in`.rest

import io.holixon.example.university.student.application.port.`in`.RegisterStudentInPort
import io.holixon.example.university.student.application.port.`in`.RetrieveMatriculationsInPort
import io.holixon.example.university.student.application.port.`in`.UnregisterStudentInPort
import io.holixon.example.university.student.domain.command.Person
import io.holixon.example.university.student.domain.query.Matriculation
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import java.time.Year

@RestController
@RequestMapping("/students")
class StudentController(
  val registerStudentInPort: RegisterStudentInPort,
  val unregisterStudentInPort: UnregisterStudentInPort,
  val retrieveMatriculationsInPort: RetrieveMatriculationsInPort
) {

  @GetMapping
  fun getAllStudents(): ResponseEntity<List<Matriculation>> {
    return ok(retrieveMatriculationsInPort.findAll())
  }

  @PostMapping
  fun register(@RequestBody dto: RegisterStudentDto): ResponseEntity<Void> {
    registerStudentInPort.registerStudent(dto.person, Year.parse(dto.year))
    return noContent().build()
  }

  @GetMapping("/number/{matriculationNumber}")
  fun getStudentByNumber(@PathVariable matriculationNumber: String): ResponseEntity<Matriculation> {
    return ok(retrieveMatriculationsInPort.findByNumber(matriculationNumber))
  }

  @DeleteMapping("/number/{matriculationNumber}")
  fun unregister(@PathVariable matriculationNumber: String): ResponseEntity<Void> {
    unregisterStudentInPort.unregisterStudent(matriculationNumber)
    return noContent().build()
  }

  data class RegisterStudentDto(
    val person: Person,
    val year: String
  )
}
