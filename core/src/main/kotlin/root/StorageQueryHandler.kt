package io.holixon.axon.eclipsestore.root

import com.google.common.io.Files
import io.holixon.axon.eclipsestore.root.StorageQueryHandler.Companion.QUERY_NAME
import mu.KLogging
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.GenericQueryMessage
import org.axonframework.queryhandling.QueryBus
import org.eclipse.store.integrations.spring.boot.types.configuration.EclipseStoreProperties
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors


@Component
class StorageQueryHandler(
  private val eclipseStoreProperties: EclipseStoreProperties
) {

  companion object : KLogging() {
    const val QUERY_NAME = "queryForBackupSnapshot"
  }

  fun findSnapshot(query: QueryForSnapshot): Snapshot? {
    logger.info { "ECLIPSE-STORE-SUPPORT: Backup requested from ${query.ownBackupLocation}." }
    return if (eclipseStoreProperties.backupDirectory == query.ownBackupLocation) {
      logger.info { "ECLIPSE-STORE-SUPPORT: Skipping response to the request of the node with the same backup directory: ${query.ownBackupLocation}." }
      null // that's me asking myself
    } else {
      if (FileSystemHelper.backupExists(eclipseStoreProperties)) {
        val bytes = Files.toByteArray(
          // FIXME -> deactivate the event processors for the time of snapshot creation.
          // FIXME -> create snapshot on schedule, send them here only
          FileSystemHelper.createBackupSnapshot(eclipseStoreProperties)
        )
        logger.info { "ECLIPSE-STORE-SUPPORT: Created backup snapshot, sending it." }
        Snapshot(
          location = eclipseStoreProperties.backupDirectory,
          bytes = bytes
        )
      } else {
        logger.warn { "ECLIPSE-STORE-SUPPORT: No backup found." }
        null
      }
    }
  }
}

fun QueryBus.registerQueryHandler(instance: StorageQueryHandler) {
  this.subscribe<Snapshot>(
    QUERY_NAME,
    Snapshot::class.java
  ) { message -> instance.findSnapshot(message.payload as QueryForSnapshot) }
}

fun QueryBus.queryForSnapshot(ownBackupLocation: String, currentTokenPosition: Long?): List<Result<Snapshot>> {
  val queryMessage =
    GenericQueryMessage(
      QueryForSnapshot(ownBackupLocation, currentTokenPosition),
      QUERY_NAME,
      ResponseTypes.optionalInstanceOf(Snapshot::class.java)
    )
  return this
    .scatterGather(queryMessage, 10, TimeUnit.SECONDS)
    .collect(Collectors.toList())
    .mapNotNull {
      if (it.isExceptional) {
        Result.failure(it.exceptionResult())
      } else {
        if (it.payload.isPresent) {
          Result.success(it.payload.get())
        } else {
          null
        }
      }
    }
}

data class QueryForSnapshot(
  val ownBackupLocation: String,
  val currentTokenPosition: Long?
)

data class Snapshot(
  val location: String,
  val bytes: ByteArray
) {

  companion object {
    fun empty(location: String) = Snapshot(location = location, bytes = byteArrayOf())
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Snapshot

    if (location != other.location) return false
    if (!bytes.contentEquals(other.bytes)) return false

    return true
  }

  override fun hashCode(): Int {
    var result = location.hashCode()
    result = 31 * result + bytes.contentHashCode()
    return result
  }
}

