package io.holixon.axon.eclipsestore.root

import com.google.common.io.Files
import io.holixon.axon.eclipsestore.root.StorageQueryHandler.Companion.QUERY_NAME
import mu.KLogging
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.GenericQueryMessage
import org.axonframework.queryhandling.QueryBus
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors


@Component
class StorageQueryHandler(
  private val projectionSupportProperties: ProjectionSupportProperties
) {

  private val eclipseStoreProperties = projectionSupportProperties.store

  companion object : KLogging() {
    const val QUERY_NAME = "queryForBackupSnapshot"
  }

  fun findSnapshot(query: QueryForSnapshot): Snapshot? {
    logger.info { "ECLIPSE-STORE-SUPPORT: Backup requested for ${projectionSupportProperties.storeKey} from ${query.instanceKey}." }
    return if (projectionSupportProperties.instanceKey == query.instanceKey) {
      logger.info { "ECLIPSE-STORE-SUPPORT: Skipping response to the request of the node with the same instance key: ${query.instanceKey}." }
      null // that's me asking myself
    } else {
      if (projectionSupportProperties.storeKey == query.storeKey && FileSystemHelper.backupExists(eclipseStoreProperties)) {
        val bytes = Files.toByteArray(
          // FIXME -> deactivate the event processors for the time of snapshot creation.
          // FIXME -> create snapshot on schedule, send them here only
          FileSystemHelper.createBackupSnapshot(eclipseStoreProperties)
        )
        logger.info { "ECLIPSE-STORE-SUPPORT: Created backup snapshot, sending it." }
        Snapshot(
          instanceKey = projectionSupportProperties.instanceKey,
          bytes = bytes
        )
      } else {
        logger.warn { "ECLIPSE-STORE-SUPPORT: No backup found for ${projectionSupportProperties.storeKey}." }
        null
      }
    }
  }
}

fun QueryBus.registerQueryHandler(storeKey: String, instance: StorageQueryHandler) {
  this.subscribe<Snapshot>(
    QUERY_NAME + storeKey,
    Snapshot::class.java
  ) { message -> instance.findSnapshot(message.payload as QueryForSnapshot) }
}

fun QueryBus.queryForSnapshot(storeKey: String, instanceKey: String): List<Result<Snapshot>> {
  val queryMessage =
    GenericQueryMessage(
      QueryForSnapshot(storeKey = storeKey, instanceKey = instanceKey),
      QUERY_NAME + storeKey,
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
  val storeKey: String,
  val instanceKey: String
)

data class Snapshot(
  val instanceKey: String,
  val bytes: ByteArray
) {

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Snapshot

    if (instanceKey != other.instanceKey) return false
    if (!bytes.contentEquals(other.bytes)) return false

    return true
  }

  override fun hashCode(): Int {
    var result = instanceKey.hashCode()
    result = 31 * result + bytes.contentHashCode()
    return result
  }
}

