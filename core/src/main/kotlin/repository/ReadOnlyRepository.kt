package io.holixon.axon.eclipsestore.repository

import kotlin.jvm.Throws

/**
 * Read-only repository.
 * @param KEY identity type.
 * @param VALUE entity type.
 */
interface ReadOnlyRepository<KEY : Any, VALUE : Any> {
  /**
   * Finds an entity by id.
   * @param id identity of the entity.
   * @throws NoSuchElementException if no entity was found by specified id.
   * @return entity with given id.
   */
  @Throws(NoSuchElementException::class)
  fun findById(id: KEY): VALUE

  /**
   * Tries to return an entity by given id, falling back to null, if no entity is found.
   * @param id identity of the entity.
   * @return entity or null
   */
  fun findByIdOrNull(id: KEY): VALUE?

  /**
   * Finds all entities.
   * @return all entities.
   */
  fun findAll(): List<VALUE>

  /**
   * Counts entities in the storage.
   * @return amount of entities.
   */
  fun countAll(): Int
}
