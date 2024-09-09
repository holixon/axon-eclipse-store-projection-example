package io.holixon.axon.eclipsestore.backup

import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import java.util.concurrent.TimeUnit
import java.util.stream.Stream

fun QueryGateway.queryForSnapshot(currentTokenPosition: Long?): Stream<Snapshot> {
  val query = QueryForSnapshot(currentTokenPosition)
  return this.scatterGather(query, ResponseTypes.instanceOf(Snapshot::class.java), 5, TimeUnit.SECONDS)
}
