package io.holixon.example.university.student.application.usecase

import io.holixon.example.university.student.application.port.`in`.SubscribeStudentInPort
import io.holixon.example.university.student.application.port.out.StudentCommandOutPort
import io.holixon.example.university.student.application.port.out.AddStudentToCourseCommand
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.concurrent.CompletableFuture

@Component
class SubscribeStudentUseCase(
  private val studentCommandOutPort: StudentCommandOutPort
): SubscribeStudentInPort {
  override fun subscribe(courseId: String, matriculationNumber: String, subscriptionDate: LocalDate, courseStart: LocalDate, courseEnd: LocalDate): CompletableFuture<Void> {
    return studentCommandOutPort.addStudentToCourse(
      AddStudentToCourseCommand(
        matriculationNumber = matriculationNumber,
        courseId = courseId,
        subscriptionDate = subscriptionDate,
        courseStartDate = courseStart,
        courseEndDate = courseEnd
      )
    )
  }

}
