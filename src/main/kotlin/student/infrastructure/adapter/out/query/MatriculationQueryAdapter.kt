package io.holixon.example.university.student.infrastructure.adapter.out.query

import io.holixon.example.university.student.application.port.out.MatriculationQueryOutPort
import io.holixon.example.university.student.domain.query.Matriculation
import io.holixon.example.university.student.infrastructure.adapter.out.query.impl.AllMatriculationsMarker
import io.holixon.example.university.student.infrastructure.adapter.out.query.impl.MatriculationByNumber
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Component
import java.util.*

@Component
class MatriculationQueryAdapter(
  private val queryGateway: QueryGateway
) : MatriculationQueryOutPort {

  override fun findAllMatriculations(): List<Matriculation> {
    return queryGateway
      .query("findMatriculations", AllMatriculationsMarker, ResponseTypes.multipleInstancesOf(Matriculation::class.java))
      .join()
  }

  override fun findMatriculation(matriculationNumber: String): Matriculation? {
    return queryGateway
      .query("findMatriculationByNumber", MatriculationByNumber(matriculationNumber = matriculationNumber), ResponseTypes.optionalInstanceOf(Matriculation::class.java))
      .join()
      .toKotlin()

  }

  fun <T> Optional<T>.toKotlin(): T? = if (this.isEmpty) {
    null
  } else {
    this.get()
  }

}
