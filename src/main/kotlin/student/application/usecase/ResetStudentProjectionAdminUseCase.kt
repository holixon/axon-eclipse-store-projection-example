package io.holixon.example.university.student.application.usecase

import io.holixon.example.university.student.application.port.`in`.ResetStudentProjectionAdminInPort
import io.holixon.example.university.student.application.port.out.StudentProjectionAdminOutPort
import io.holixon.example.university.student.application.port.out.TimetableProjectionAdminOutPort
import org.springframework.stereotype.Component

@Component
class ResetStudentProjectionAdminUseCase(
  private val studentProjectionAdminOutPort: StudentProjectionAdminOutPort,
  private val timetableProjectionAdminOutPort: TimetableProjectionAdminOutPort
  ) : ResetStudentProjectionAdminInPort {

  override fun resetStudentProjection() {
    studentProjectionAdminOutPort.resetStudentProjection()
  }

  override fun resetTimetableProjection() {
    timetableProjectionAdminOutPort.resetTimetableProjection()
  }
}
