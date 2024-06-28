package io.holixon.example.university.student.application.usecase

import io.holixon.example.university.student.application.port.`in`.UnsubscribeStudentInPort
import io.holixon.example.university.student.application.port.out.StudentCommandOutPort
import io.holixon.example.university.student.application.port.out.RemoveStudentFromCourseCommand
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.concurrent.CompletableFuture

@Component
class UnsubscribeStudentUseCase(
  private val studentCommandOutPort: StudentCommandOutPort
): UnsubscribeStudentInPort {
  override fun unsubscribe(courseId: String, matriculationNumber: String, unsubscriptionDate: LocalDate): CompletableFuture<Void> {
    return studentCommandOutPort.removeStudentFromCourse(
      RemoveStudentFromCourseCommand(
        matriculationNumber = matriculationNumber,
        courseId = courseId,
        unsubscriptionDate = unsubscriptionDate
      )
    )
  }

}
