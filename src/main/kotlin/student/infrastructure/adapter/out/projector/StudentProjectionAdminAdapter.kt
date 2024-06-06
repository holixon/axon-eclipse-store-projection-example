package io.holixon.example.university.student.infrastructure.adapter.out.projector

import io.holixon.example.university.course.infrastructure.support.axon.ProcessorSupport
import io.holixon.example.university.student.application.port.out.StudentProjectionAdminOutPort
import mu.KLogging
import org.springframework.stereotype.Component

@Component
class StudentProjectionAdminAdapter(
  private val studentProjectorRepository: StudentProjectorRepository,
  private val processorSupport: ProcessorSupport
) : StudentProjectionAdminOutPort {

  companion object : KLogging()

  override fun resetStudentProjection() {
    logger.info { "[STUDENT ADMIN ADAPTER]: There are currently ${studentProjectorRepository.countAll()} students available." }
    logger.info { "[STUDENT ADMIN ADAPTER]: Deleting students from projection..." }
    studentProjectorRepository.deleteAll()
    logger.info { "[STUDENT ADMIN ADAPTER]: There are currently ${studentProjectorRepository.countAll()} students available." }
    studentProjectorRepository.countAll()
    logger.info { "[STUDENT ADMIN ADAPTER]: Triggering replay for the student projection." }
    processorSupport.replay(StudentProjector.GROUP)
  }
}
