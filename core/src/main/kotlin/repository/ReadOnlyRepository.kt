package io.holixon.axon.eclipsestore.repository

interface ReadOnlyRepository<KEY : Any, VALUE : Any> {
  fun findById(id: KEY): VALUE
  fun findByIdOrNull(id: KEY): VALUE?
  fun findAll(): List<VALUE>
  fun countAll(): Int
}
