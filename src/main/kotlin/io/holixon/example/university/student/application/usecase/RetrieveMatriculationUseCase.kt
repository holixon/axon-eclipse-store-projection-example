package io.holixon.example.university.student.application.usecase

import io.holixon.example.university.student.application.port.`in`.RetrieveMatriculationsInPort
import io.holixon.example.university.student.application.port.out.MatriculationQueryOutPort
import io.holixon.example.university.student.domain.query.Matriculation
import org.springframework.stereotype.Component

@Component
class RetrieveMatriculationUseCase(
  val matriculationQueryOutPort: MatriculationQueryOutPort
) : RetrieveMatriculationsInPort {
  override fun findByNumber(matriculationNumber: String): Matriculation? =
    matriculationQueryOutPort.findMatriculation(matriculationNumber = matriculationNumber)

  override fun findAll(): List<Matriculation> =
    matriculationQueryOutPort.findAllMatriculations()
}
