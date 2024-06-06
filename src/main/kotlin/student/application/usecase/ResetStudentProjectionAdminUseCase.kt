package io.holixon.example.university.student.application.usecase

import io.holixon.example.university.student.application.port.`in`.ResetStudentProjectionAdminInPort
import io.holixon.example.university.student.application.port.out.StudentProjectionAdminOutPort
import org.springframework.stereotype.Component

@Component
class ResetStudentProjectionAdminUseCase(
  private val studentProjectionAdminOutPort: StudentProjectionAdminOutPort
) : ResetStudentProjectionAdminInPort {

  override fun resetStudentProjection() {
    studentProjectionAdminOutPort.resetStudentProjection()
  }
}
