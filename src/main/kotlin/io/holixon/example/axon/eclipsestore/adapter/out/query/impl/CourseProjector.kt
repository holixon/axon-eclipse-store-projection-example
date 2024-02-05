package io.holixon.example.axon.eclipsestore.adapter.out.query.impl

import io.holixon.example.axon.eclipsestore.adapter.out.query.impl.CourseProjector.Companion.GROUP
import io.holixon.example.axon.eclipsestore.domain.event.CourseCapacityChangedEvent
import io.holixon.example.axon.eclipsestore.domain.event.CourseCreatedEvent
import io.holixon.example.axon.eclipsestore.domain.query.Course
import mu.KLogging
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
@ProcessingGroup(GROUP)
class CourseProjector(
  private val courseProjectionRepository: CourseProjectionRepository
) {

  companion object : KLogging() {
    const val GROUP = "CourseProjector"
  }

  @EventHandler
  fun on(event: CourseCreatedEvent) {
    logger.info { "[COURSE PROJECTOR]: Storing new course '${event.name}'." }
    courseProjectionRepository.save(
      Course(
        id = event.id,
        name = event.name,
        start = event.start,
        end = event.end,
        maxCapacity = event.maxStudents
      )
    )
  }

  @EventHandler
  fun on(event: CourseCapacityChangedEvent) {
    courseProjectionRepository.findById(event.id)?.let {
      logger.info { "[COURSE PROJECTOR]: Changing capacity of '${it.name}' to ${event.maxStudents}." }
      courseProjectionRepository.save(
        it.copy(maxCapacity = event.maxStudents)
      )
    }
  }
}
