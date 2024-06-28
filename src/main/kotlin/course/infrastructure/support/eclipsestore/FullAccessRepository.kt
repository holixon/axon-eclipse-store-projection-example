package io.holixon.example.university.course.infrastructure.support.eclipsestore

interface FullAccessRepository<KEY : Any, VALUE : Any> : ReadOnlyRepository<KEY, VALUE> {
  fun save(value: VALUE): VALUE
  fun deleteAll()
  fun deleteById(id: KEY): VALUE?
}
