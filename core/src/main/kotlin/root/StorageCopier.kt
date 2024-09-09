package io.holixon.axon.eclipsestore.root

import io.holixon.axon.eclipsestore.backup.queryForSnapshot
import mu.KLogging
import org.axonframework.queryhandling.QueryGateway
import org.eclipse.store.integrations.spring.boot.types.configuration.EclipseStoreProperties
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager
import org.eclipse.store.storage.types.StorageManager
import org.springframework.stereotype.Component
import java.io.File


@Component
class StorageCopier(
  private val queryGateway: QueryGateway,
  private val eclipseStoreProperties: EclipseStoreProperties,
  private val embeddedStorageManager: EmbeddedStorageManager
) {
  companion object : KLogging()

  fun getStorageCopy() {
    queryGateway.queryForSnapshot(null).findAny().ifPresent {
      // found a response
      logger.info { "ECLIPSE-STORE-SUPPORT: Received response $it" }
    }
  }

  fun storageNotPresent(): Boolean {
    val location = File(eclipseStoreProperties.storageDirectory)
    return embeddedStorageManager.root() != null

    // !location.exists()
  }


}
