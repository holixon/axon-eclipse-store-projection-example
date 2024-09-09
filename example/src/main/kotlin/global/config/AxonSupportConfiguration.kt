package io.holixon.example.university.global.config

import io.holixon.example.university.course.infrastructure.adapter.out.projector.CourseProjector
import io.holixon.example.university.global.support.axon.ProcessorSupport
import io.holixon.example.university.student.infrastructure.adapter.`in`.event.CourseSubscriptionProcessManager
import io.holixon.example.university.student.infrastructure.adapter.out.projector.StudentProjector
import io.holixon.example.university.student.infrastructure.adapter.out.projector.TimetableProjector
import mu.KLogging
import org.axonframework.config.EventProcessingConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Configuration
@EnableScheduling
class AxonSupportConfiguration {

  companion object : KLogging() {
    const val TOKEN_STORE_NAME = "university-application"
  }

  @Bean
  fun processorSupport(eventProcessingConfiguration: EventProcessingConfiguration): ProcessorSupport {
    return ProcessorSupport(eventProcessingConfiguration = eventProcessingConfiguration)
  }

  @Bean
  fun scheduler(processorSupport: ProcessorSupport) = StartingScheduler(processorSupport)

  class StartingScheduler(private val processorSupport: ProcessorSupport) {
    @Scheduled(initialDelay = 30_000, fixedDelay = 1000_000_000)
    fun start() {
      logger.info { "Starting processors" }
      processorSupport.startProcessor(CourseSubscriptionProcessManager.GROUP)
      processorSupport.startProcessor(CourseProjector.GROUP)
      processorSupport.startProcessor(StudentProjector.GROUP)
      processorSupport.startProcessor(TimetableProjector.GROUP)
    }
  }
}
