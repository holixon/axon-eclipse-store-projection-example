package io.holixon.example.university.student.infrastructure.adapter.out.query

import io.holixon.example.university.student.application.port.out.TimetableOutPort
import io.holixon.example.university.student.domain.query.Timetable
import io.holixon.example.university.student.infrastructure.adapter.out.query.impl.TimetableByMatriculationNumber
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Component
import java.util.*

@Component
class TimetableQueryAdapter(
  private val queryGateway: QueryGateway
) : TimetableOutPort {

  override fun getTimetableByMatriculationNumber(matriculationNumber: String): Timetable? {
    return queryGateway
      .query(
        "findTimetable",
        TimetableByMatriculationNumber(matriculationNumber = matriculationNumber),
        ResponseTypes.optionalInstanceOf(Timetable::class.java)
      ).join()
      .toKotlin()
  }

  fun <T> Optional<T>.toKotlin(): T? = if (this.isEmpty) {
    null
  } else {
    this.get()
  }

}
