package io.holixon.example.university.student.infrastructure.adapter.out.query.impl

import io.holixon.example.university.student.domain.query.Matriculation

interface MatriculationProjectionRepository {
  fun findById(id: String): Matriculation?
  fun findAll(): List<Matriculation>

}
