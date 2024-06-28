package io.holixon.example.university.course.infrastructure.support.eclipsestore

interface ReadOnlyRepository<KEY : Any, VALUE : Any> {
  fun findById(id: KEY): VALUE
  fun findByIdOrNull(id: KEY): VALUE?
  fun findAll(): List<VALUE>
  fun countAll(): Int
}
