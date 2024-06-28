package io.holixon.example.university.student.infrastructure.adapter.out.projector

import io.holixon.example.university.student.domain.event.StudentRegisteredEvent
import io.holixon.example.university.student.domain.event.StudentUnregisteredEvent
import io.holixon.example.university.student.domain.query.Matriculation
import io.holixon.example.university.student.infrastructure.adapter.out.projector.StudentProjector.Companion.GROUP
import mu.KLogging
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.eventhandling.ResetHandler
import org.springframework.stereotype.Component

@Component
@ProcessingGroup(GROUP)
class StudentProjector(
  val studentProjectorRepository: StudentProjectorRepository
) {

  companion object : KLogging() {
    const val GROUP = "StudentProjector"
  }

  @EventHandler
  fun on(event: StudentRegisteredEvent) {
    logger.info { "[STUDENT PROJECTOR]: New student registered ${event.person.firstName} ${event.person.lastName}, ${event.matriculationNumber}." }
    studentProjectorRepository.save(
      Matriculation(
        matriculationNumber = event.matriculationNumber,
        firstName = event.person.firstName,
        lastName = event.person.lastName,
        year = event.year
      )
    )
  }

  @EventHandler
  fun on(event: StudentUnregisteredEvent) {
    studentProjectorRepository.findById(event.matriculationNumber)?.let {
      logger.info { "[STUDENT PROJECTOR]: Student unregistered ${it.firstName} ${it.lastName}, ${it.matriculationNumber}." }
      studentProjectorRepository.deleteById(it.matriculationNumber)
    }
  }

  @ResetHandler
  fun reset() {
    logger.info { "[STUDENT PROJECTOR]: Resetting." }
    studentProjectorRepository.deleteAll()
  }
}
