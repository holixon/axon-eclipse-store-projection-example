package io.holixon.example.university.course.application.usecase

import io.holixon.example.university.course.application.port.out.ChangeCourseCapacityCommand
import io.holixon.example.university.course.application.port.`in`.ModifyCourseInPort
import io.holixon.example.university.course.application.port.out.CoursesCommandOutPort
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class ModifyCourseUseCase(
  private val coursesCommandOutPort: CoursesCommandOutPort
) : ModifyCourseInPort {
  override fun changeNumberOfStudents(id: String, maxStudents: Int): CompletableFuture<Void> =
    coursesCommandOutPort.modifyCourse(
      ChangeCourseCapacityCommand(
        id = id,
        maxStudents = maxStudents
      )
    )
}
