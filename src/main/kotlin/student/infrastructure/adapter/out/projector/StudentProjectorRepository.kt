package io.holixon.example.university.student.infrastructure.adapter.out.projector


import io.holixon.example.university.student.domain.query.Matriculation

/**
 * Student repository.
 */
interface StudentProjectorRepository {
  fun findById(id: String): Matriculation?
  fun save(value: Matriculation): Matriculation
  fun deleteById(id: String): Matriculation?
  fun deleteAll()
  fun countAll(): Int
}

