package io.holixon.example.university.student.infrastructure.adapter.out.query.impl

import io.holixon.example.university.student.domain.query.Timetable
import io.holixon.example.university.student.infrastructure.adapter.out.query.impl.TimetableQueryHandler.Companion.GROUP
import org.axonframework.config.ProcessingGroup
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component
import java.util.*

@Component
@ProcessingGroup(GROUP)
class TimetableQueryHandler(
  private val timetableRepository: TimetableProjectionRepository
) {
  companion object {
    const val GROUP = "timetable"
  }

  @QueryHandler(queryName = "findTimetable")
  fun findTimetable(query: TimetableByMatriculationNumber): Optional<Timetable> {
    return Optional.ofNullable(timetableRepository.findByIdOrNull(matriculationNumber = query.matriculationNumber))
  }
}
