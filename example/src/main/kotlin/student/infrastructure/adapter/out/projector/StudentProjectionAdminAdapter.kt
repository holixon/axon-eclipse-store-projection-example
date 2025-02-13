package io.holixon.example.university.student.infrastructure.adapter.out.projector

import io.github.oshai.kotlinlogging.KotlinLogging
import io.holixon.example.university.global.support.axon.ProcessorSupport
import io.holixon.example.university.student.application.port.out.StudentProjectionAdminOutPort
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class StudentProjectionAdminAdapter(
  private val studentProjectorRepository: StudentProjectorRepository,
  private val processorSupport: ProcessorSupport
) : StudentProjectionAdminOutPort {

  override fun resetStudentProjection() {
    logger.info { "[STUDENT ADMIN ADAPTER]: There are currently ${studentProjectorRepository.countAll()} students available." }
    logger.info { "[STUDENT ADMIN ADAPTER]: Triggering replay for the student projection." }
    processorSupport.replay(StudentProjector.GROUP)
  }
}
