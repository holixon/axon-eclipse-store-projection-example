package io.holixon.example.university.course.application.usecase

import io.holixon.example.university.course.application.port.`in`.ResetCourseProjectionAdminInPort
import io.holixon.example.university.course.application.port.out.CourseProjectionAdminOutPort
import org.springframework.stereotype.Component

@Component
class ResetProjectionAdminUseCase(
  private val courseProjectionAdminOutPort: CourseProjectionAdminOutPort
) : ResetCourseProjectionAdminInPort {

  override fun resetCourseProjection() {
    courseProjectionAdminOutPort.resetCourseProjection()
  }
}
