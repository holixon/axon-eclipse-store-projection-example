package io.holixon.example.university.course.application.usecase

import io.holixon.example.university.course.application.port.`in`.ResetProjectionAdminInPort
import io.holixon.example.university.course.application.port.out.CourseProjectionAdminOutPort
import org.springframework.stereotype.Component

@Component
class ResetProjectionAdminUseCase(
  private val courseProjectionAdminOutPort: CourseProjectionAdminOutPort
) : ResetProjectionAdminInPort {


  override fun resetCourseProjection() {
    courseProjectionAdminOutPort.resetProjection()
  }
}
