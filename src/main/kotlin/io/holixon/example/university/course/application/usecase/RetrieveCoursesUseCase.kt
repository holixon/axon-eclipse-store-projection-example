package io.holixon.example.university.course.application.usecase

import io.holixon.example.university.course.application.port.`in`.RetrieveCoursesInPort
import io.holixon.example.university.course.application.port.out.CoursesQueryOutPort
import io.holixon.example.university.course.domain.query.Course
import org.springframework.stereotype.Component

@Component
class RetrieveCoursesUseCase(
  private val coursesQueryOutPort: CoursesQueryOutPort
) : RetrieveCoursesInPort {

  override fun getCourseById(id: String): Course? {
    return coursesQueryOutPort.getCourseById(id)
  }

  override fun getAllCourses(): List<Course> {
    return coursesQueryOutPort.getAllCourses()
  }
}
