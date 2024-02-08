package io.holixon.example.axon.eclipsestore.adapter.out.project

import io.holixon.example.axon.eclipsestore.application.port.out.CourseProjectionAdminOutPort
import io.holixon.example.axon.eclipsestore.infrastructure.axon.ProcessorSupport
import mu.KLogging
import org.springframework.stereotype.Component

@Component
class CourseProjectionAdminAdapter(
  private val courseProjectorRepository: CourseProjectorRepository,
  private val processorSupport: ProcessorSupport
) : CourseProjectionAdminOutPort {

  companion object: KLogging()

  override fun resetProjection() {
    logger.info { "[COURSE ADMIN ADAPTER]: There are currently ${courseProjectorRepository.countAll()} courses available." }
    logger.info { "[COURSE ADMIN ADAPTER]: Deleting courses from projection..." }
    courseProjectorRepository.deleteAll()
    logger.info { "[COURSE ADMIN ADAPTER]: There are currently ${courseProjectorRepository.countAll()} courses available." }
    courseProjectorRepository.countAll()
    logger.info { "[COURSE ADMIN ADAPTER]: Triggering replay for the course projection." }
    processorSupport.replay(CourseProjector.GROUP)
  }
}
