package io.holixon.example.university.student.infrastructure.adapter.out.projector

import io.holixon.example.university.global.support.axon.ProcessorSupport
import io.holixon.example.university.student.application.port.out.TimetableProjectionAdminOutPort
import org.springframework.stereotype.Component

@Component
class TimetableProjectionAdminAdapter(
  private val processorSupport: ProcessorSupport
) : TimetableProjectionAdminOutPort {
  override fun resetTimetableProjection() {
    processorSupport.replay(TimetableProjector.GROUP)
  }
}
