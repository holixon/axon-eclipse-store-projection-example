package io.holixon.example.university.course.application.usecase

import io.holixon.example.university.course.application.port.`in`.CreateCourseInPort
import io.holixon.example.university.course.application.port.out.CoursesCommandOutPort
import io.holixon.example.university.course.application.port.out.CreateCourseCommand
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.concurrent.CompletableFuture

@Component
class CreateCourseUseCase(
  private val coursesCommandOutPort: CoursesCommandOutPort
) : CreateCourseInPort {

  override fun createCourse(id: String, name: String, maxStudents: Int, start: LocalDate, end: LocalDate): CompletableFuture<Void> =
    coursesCommandOutPort.createCourse(
      CreateCourseCommand(
        id = id,
        name = name,
        maxStudents = maxStudents,
        start = start,
        end = end
      )
    )
}
