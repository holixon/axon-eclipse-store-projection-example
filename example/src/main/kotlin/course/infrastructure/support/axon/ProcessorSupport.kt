package io.holixon.example.university.course.infrastructure.support.axon

import mu.KLogging
import org.axonframework.config.EventProcessingConfiguration
import org.axonframework.eventhandling.GapAwareTrackingToken
import org.axonframework.eventhandling.TrackingEventProcessor
import org.axonframework.eventhandling.tokenstore.UnableToClaimTokenException
import java.util.*

class ProcessorSupport(
  private val eventProcessingConfiguration: EventProcessingConfiguration,
) {

  companion object : KLogging()

  fun replay(processorName: String, index: Long = 0): Boolean {
    getTrackingEventProcessor(processorName).apply {
      if (!this.isRunning) {
        logger.warn { "Tracking event processor $processorName is not running in current instance or not running at all" }
        return false
      }
      shutDown()
      return try {
        if (index > 0) {
          resetTokens(GapAwareTrackingToken.newInstance(index - 1, Collections.emptySortedSet()))
        } else {
          resetTokens()
        }
        true
      } catch (e: UnableToClaimTokenException) {
        logger.warn("Unable to claim token for trackingEventProcessor $processorName on id ${index - 1}", e);
        false;
      } finally {
        start()
      }
    }
  }

  fun startProcessor(processorName: String) {
    val processor = getTrackingEventProcessor(processorName)
    if (!processor.isRunning) {
      processor.start()
    }
  }

  fun shutdownProcessor(processorName: String) {
    val processor = getTrackingEventProcessor(processorName)
    if (processor.isRunning) {
      processor.shutDown()
    }
  }


  private fun getTrackingEventProcessor(processorName: String): TrackingEventProcessor {
    return this.eventProcessingConfiguration
      .eventProcessor(processorName, TrackingEventProcessor::class.java)
      .orElseThrow { TrackingEventProcessorNotFoundException(processorName) }
  }


  class TrackingEventProcessorNotFoundException(processorName: String) : RuntimeException("Tracking processor $processorName not found.")
}
