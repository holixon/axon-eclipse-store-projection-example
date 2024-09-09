package io.holixon.axon.eclipsestore.backup

import org.axonframework.queryhandling.QueryHandler
import org.eclipse.store.integrations.spring.boot.types.configuration.EclipseStoreProperties

class StorageQueryHandler(val eclipseStoreProperties: EclipseStoreProperties) {

  @QueryHandler
  fun findSnapshot(query: QueryForSnapshot): Snapshot {
    // FIXME
    return Snapshot("test".toByteArray())
  }
}

data class QueryForSnapshot(val currentTokenPosition: Long?)

data class Snapshot(val bytes: ByteArray)

