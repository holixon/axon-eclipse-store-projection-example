package io.holixon.example.university.student.infrastructure.adapter.out.query.impl

import io.holixon.example.university.student.domain.query.Matriculation
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component
import java.util.*

@Component
class MatriculationQueryHandler(
  val matriculationProjectionRepository: MatriculationProjectionRepository
) {

  @QueryHandler(queryName = "findMatriculations")
  fun findCourses(query: AllMatriculationsMarker): MutableList<Matriculation> {
    return matriculationProjectionRepository.findAll().toMutableList()
  }

  @QueryHandler(queryName = "findMatriculationByNumber")
  fun findCourseById(query: MatriculationByNumber): Optional<Matriculation> {
    return Optional.ofNullable(matriculationProjectionRepository.findById(query.matriculationNumber))
  }

}
