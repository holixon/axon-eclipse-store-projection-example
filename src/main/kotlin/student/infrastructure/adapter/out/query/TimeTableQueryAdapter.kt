package io.holixon.example.university.student.infrastructure.adapter.out.query

import io.holixon.example.university.student.application.port.out.TimeTableOutPort
import io.holixon.example.university.student.domain.query.Matriculation
import io.holixon.example.university.student.domain.query.Timetable
import io.holixon.example.university.student.infrastructure.adapter.out.query.impl.AllMatriculationsMarker
import io.holixon.example.university.student.infrastructure.adapter.out.query.impl.TimetableByMatriculationNumber
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Component
import java.util.Optional
import java.util.concurrent.CompletableFuture

@Component
class TimeTableQueryAdapter(
  private val queryGateway: QueryGateway
) : TimeTableOutPort {
  override fun getTableName(matriculationNumber: String): CompletableFuture<Optional<Timetable>> {
    return queryGateway
      .query(
        "findTimetable",
        TimetableByMatriculationNumber(matriculationNumber = matriculationNumber),
        ResponseTypes.optionalInstanceOf(Timetable::class.java)
      )

  }
}
