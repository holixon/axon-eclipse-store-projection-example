package io.holixon.axon.eclipsestore.repository

/**
 * Full access repository providing read and write operations.
 * @param KEY identity type.
 * @param VALUE entity type.
 */
interface FullAccessRepository<KEY : Any, VALUE : Any> : ReadOnlyRepository<KEY, VALUE> {
  /**
   * Saves the entity.
   * @param value value to save.
   * @return stored value.
   */
  fun save(value: VALUE): VALUE

  /**
   * Delete all entities.
   */
  fun deleteAll()

  /**
   * Deletes an entity by given id.
   * @param id entity id.
   * @return deleted entity or null, if no entity with given id was stored and deleted.
   */
  fun deleteById(id: KEY): VALUE?
}
