package io.holixon.example.university.student.infrastructure.adapter.out.projector

import io.holixon.example.university.student.domain.event.StudentJoinedCourseEvent
import io.holixon.example.university.student.domain.event.StudentLeftCourseEvent
import io.holixon.example.university.student.domain.query.Timetable
import io.holixon.example.university.student.infrastructure.adapter.out.projector.TimetableProjector.Companion.GROUP
import mu.KLogging
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.eventhandling.ResetHandler
import org.springframework.stereotype.Component

@Component
@ProcessingGroup(GROUP)
class TimetableProjector(
  private val timetableProjectorRepository: TimetableProjectorRepository
) {
  companion object : KLogging() {
    const val GROUP = "TimetableProjector"
  }

  @EventHandler
  fun on(evt: StudentJoinedCourseEvent) {
    val timetable = (timetableProjectorRepository.findByIdOrNull(evt.matriculationNumber) ?: Timetable(matriculationNumber = evt.matriculationNumber)).addCourse(
      evt.courseId
    )
    logger.info { "[TIMETABLE PROJECTOR]: Student ${evt.matriculationNumber} joined course ${evt.courseId}." }
    timetableProjectorRepository.save(timetable)
  }

  @EventHandler
  fun on(evt: StudentLeftCourseEvent) {
    timetableProjectorRepository.findByIdOrNull(evt.matriculationNumber)?.let {
      logger.info { "[TIMETABLE PROJECTOR]: Student ${evt.matriculationNumber} left course ${evt.courseId}." }
      timetableProjectorRepository.save(it.removeCourse(evt.courseId))
    }
  }

  @ResetHandler
  fun onReset() {
    logger.info { "[TIMETABLE PROJECTOR]: Resetting." }
    timetableProjectorRepository.deleteAll()
  }

}
