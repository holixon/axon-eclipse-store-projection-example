package io.holixon.example.university.student.infrastructure.adapter.out.query

import io.holixon.example.university.student.application.port.out.MatriculationQueryOutPort
import io.holixon.example.university.student.domain.query.Matriculation
import io.holixon.example.university.student.infrastructure.adapter.out.query.impl.AllMatriculationsMarker
import io.holixon.example.university.student.infrastructure.adapter.out.query.impl.MatriculationByNumber
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.CompletableFuture

@Component
class MatriculationQueryAdapter(
  private val queryGateway: QueryGateway
) : MatriculationQueryOutPort {

  override fun findAllMatriculations(): CompletableFuture<List<Matriculation>> {
    return queryGateway
      .query(
        "findMatriculations",
        AllMatriculationsMarker,
        ResponseTypes.multipleInstancesOf(Matriculation::class.java)
      )
  }

  override fun findMatriculation(matriculationNumber: String): CompletableFuture<Matriculation?> {
    return queryGateway
      .query("findMatriculationByNumber",
        MatriculationByNumber(matriculationNumber = matriculationNumber),
        ResponseTypes.optionalInstanceOf(Matriculation::class.java)
      )
      .thenApply { it.toKotlin() }
  }

  fun <T> Optional<T>.toKotlin(): T? = if (this.isEmpty) {
    null
  } else {
    this.get()
  }

}
