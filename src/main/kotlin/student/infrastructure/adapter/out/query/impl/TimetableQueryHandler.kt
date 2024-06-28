package io.holixon.example.university.student.infrastructure.adapter.out.query.impl

import io.holixon.example.university.student.domain.query.Timetable
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component
import java.util.*

@Component
class TimetableQueryHandler(
  private val timetableRepository: TimetableProjectionRepository
) {

  @QueryHandler(queryName = "findTimetable")
  fun findTimetable(query: TimetableByMatriculationNumber): Optional<Timetable> {
    return Optional.ofNullable(timetableRepository.findByIdOrNull(id = query.matriculationNumber))
  }
}
