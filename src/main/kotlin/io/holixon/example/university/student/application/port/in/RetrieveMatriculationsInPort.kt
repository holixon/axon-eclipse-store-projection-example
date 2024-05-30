package io.holixon.example.university.student.application.port.`in`

import io.holixon.example.university.student.domain.query.Matriculation

interface RetrieveMatriculationsInPort {

  fun findByNumber(matriculationNumber: String): Matriculation?

  fun findAll(): List<Matriculation>
}
