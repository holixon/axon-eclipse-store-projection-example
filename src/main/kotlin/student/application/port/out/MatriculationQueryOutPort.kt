package io.holixon.example.university.student.application.port.out

import io.holixon.example.university.student.domain.query.Matriculation

interface MatriculationQueryOutPort {

  fun findMatriculation(matriculationNumber: String): Matriculation?

  fun findAllMatriculations(): List<Matriculation>
}
