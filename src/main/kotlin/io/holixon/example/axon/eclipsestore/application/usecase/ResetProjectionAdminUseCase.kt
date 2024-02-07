package io.holixon.example.axon.eclipsestore.application.usecase

import io.holixon.example.axon.eclipsestore.application.port.`in`.ResetProjectionAdminInPort
import io.holixon.example.axon.eclipsestore.application.port.out.CourseProjectionAdminOutPort
import org.springframework.stereotype.Component

@Component
class ResetProjectionAdminUseCase(
  private val courseProjectionAdminOutPort: CourseProjectionAdminOutPort
) : ResetProjectionAdminInPort {


  override fun resetCourseProjection() {
    courseProjectionAdminOutPort.resetProjection()
  }
}
