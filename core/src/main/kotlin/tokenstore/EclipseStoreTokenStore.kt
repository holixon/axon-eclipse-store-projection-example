package io.holixon.axon.eclipsestore.tokenstore

import io.holixon.axon.eclipsestore.root.StorageRoot
import mu.KLogging
import org.axonframework.eventhandling.GlobalSequenceTrackingToken
import org.axonframework.eventhandling.TrackingToken
import org.axonframework.eventhandling.tokenstore.TokenStore
import org.axonframework.eventhandling.tokenstore.UnableToClaimTokenException
import org.axonframework.eventhandling.tokenstore.UnableToInitializeTokenException
import org.axonframework.messaging.unitofwork.CurrentUnitOfWork
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Axon Token Store implementation for storing tokens of event processor tokens using Eclipse Store.
 * @param name name of the token store, to support multiple stores.
 * @param storageRoot storage root provided by the infrastructure.
 */
class EclipseStoreTokenStore(
  private val name: String,
  private val storageRoot: StorageRoot
) : TokenStore {

  companion object : KLogging() {
    const val TOKEN = "tokenstore-"
    val NULL_TOKEN = GlobalSequenceTrackingToken(-1L)
  }

  init {
    if (!storageRoot.contains(TOKEN + name)) {
      logger.debug("[TOKEN STORE] No token store ${TOKEN + name} found in storage, initializing a new one")
      val tokenStore = TokenStore(identifier = UUID.randomUUID().toString(), tokens = ConcurrentHashMap())
      storageRoot.set(TOKEN + name, tokenStore)
    } else {
      logger.debug { "[TOKEN STORE] Found token store ${TOKEN + name} with the following processors and segments: ${getTokenStore().tokens.toList().joinToString(", ")}." }
    }
  }

  @Throws(UnableToClaimTokenException::class)
  override fun initializeTokenSegments(processorName: String, segmentCount: Int) {
    this.initializeTokenSegments(processorName, segmentCount, null)
  }

  @Throws(UnableToClaimTokenException::class)
  override fun initializeTokenSegments(processorName: String, segmentCount: Int, initialToken: TrackingToken?) {
    if (fetchSegments(processorName).isNotEmpty()) {
      throw UnableToClaimTokenException("Could not initialize segments. Some segments were already present.")
    } else {

      val tokenStore = getTokenStore()
      for (segment in 0 until segmentCount) {
        tokenStore.tokens[ProcessorAndSegment(processorName, segment)] = initialToken ?: NULL_TOKEN
      }
      changeTokens(tokenStore)
    }
  }

  override fun storeToken(token: TrackingToken?, processorName: String, segment: Int) {
    logger.debug("[TOKEN STORE] Storing token for $processorName and segment $segment")
    if (CurrentUnitOfWork.isStarted()) {
      CurrentUnitOfWork.get().afterCommit {
        val tokenStore = getTokenStore()
        tokenStore.tokens[ProcessorAndSegment(processorName, segment)] = token ?: NULL_TOKEN
        changeTokens(tokenStore)
      }
    } else {
      val tokenStore = getTokenStore()
      tokenStore.tokens[ProcessorAndSegment(processorName, segment)] = token ?: NULL_TOKEN
      changeTokens(tokenStore)
    }
  }

  override fun fetchToken(processorName: String, segment: Int): TrackingToken? {
    logger.debug("[TOKEN STORE] Fetching token for $processorName and segment $segment")
    val trackingToken = getTokenStore().tokens[ProcessorAndSegment(processorName, segment)]
    return if (trackingToken == null) {
      throw UnableToClaimTokenException("No token was initialized for segment $segment for processor $processorName")
    } else if (NULL_TOKEN === trackingToken) {
      null
    } else {
      trackingToken
    }
  }

  override fun releaseClaim(processorName: String, segment: Int) {
    // no-op, the in-memory implementation isn't accessible by multiple processes
  }

  override fun deleteToken(processorName: String, segment: Int) {
    val tokenStore = getTokenStore()
    tokenStore.tokens.remove(ProcessorAndSegment(processorName, segment))
    changeTokens(tokenStore)
  }

  @Throws(UnableToInitializeTokenException::class)
  override fun initializeSegment(token: TrackingToken?, processorName: String, segment: Int) {
    logger.debug("[TOKEN STORE] Initialize segment $segment for processor $processorName")
    val tokenStore = getTokenStore()
    if (tokenStore.tokens.putIfAbsent(ProcessorAndSegment(processorName, segment), token ?: NULL_TOKEN) != null) {
      throw UnableToInitializeTokenException("Token was already present")
    }
    changeTokens(tokenStore)
  }

  override fun requiresExplicitSegmentInitialization(): Boolean = true

  override fun fetchSegments(processorName: String): IntArray {
    return getTokenStore().tokens.keys.asSequence().filter { it.processor == processorName }
      .distinct()
      .map { it.segment }
      .sorted()
      .toList()
      .toIntArray()
  }

  override fun retrieveStorageIdentifier(): Optional<String> {
    return Optional.of<String>(getTokenStore().identifier)
  }

  private fun getTokenStore(): TokenStore = storageRoot.get(TOKEN + name)

  private fun changeTokens(tokenStore: TokenStore) {
    storageRoot.append(tokenStore.tokens)
  }

  internal data class ProcessorAndSegment(val processor: String, val segment: Int)

  internal data class TokenStore(val identifier: String, val tokens: ConcurrentHashMap<ProcessorAndSegment, TrackingToken>)
}
