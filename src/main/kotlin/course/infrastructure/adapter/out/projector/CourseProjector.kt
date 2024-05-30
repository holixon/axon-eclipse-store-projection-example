package io.holixon.example.university.course.infrastructure.adapter.out.projector

import io.holixon.example.university.course.infrastructure.adapter.out.projector.CourseProjector.Companion.GROUP
import io.holixon.example.university.course.domain.event.CourseCapacityChangedEvent
import io.holixon.example.university.course.domain.event.CourseCreatedEvent
import io.holixon.example.university.course.domain.query.Course
import mu.KLogging
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
@ProcessingGroup(GROUP)
class CourseProjector(
  private val courseProjectorRepository: CourseProjectorRepository
) {

  companion object : KLogging() {
    const val GROUP = "CourseProjector"
  }

  @EventHandler
  fun on(event: CourseCreatedEvent) {
    logger.info { "[COURSE PROJECTOR]: Storing new course '${event.name}'." }
    courseProjectorRepository.save(
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
    courseProjectorRepository.findById(event.id)?.let {
      logger.info { "[COURSE PROJECTOR]: Changing capacity of '${it.name}' to ${event.maxStudents}." }
      courseProjectorRepository.save(
        it.copy(maxCapacity = event.maxStudents)
      )
    }
  }
}
