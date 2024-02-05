package io.holixon.example.axon.eclipsestore.adapter.out.query.client

import io.holixon.example.axon.eclipsestore.adapter.out.query.impl.api.AllCoursesMarker
import io.holixon.example.axon.eclipsestore.adapter.out.query.impl.api.CourseById
import io.holixon.example.axon.eclipsestore.application.port.out.CoursesQueryOutPort
import io.holixon.example.axon.eclipsestore.domain.query.Course
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Component
import java.util.*

@Component
class CourseQueryAdapter(
  private val queryGateway: QueryGateway
) : CoursesQueryOutPort {

  override fun getCourseById(id: String): Course? {
    return queryGateway
      .query("findCourseById", CourseById(id = id), ResponseTypes.optionalInstanceOf(Course::class.java))
      .join()
      .toKotlin()
  }

  override fun getAllCourses(): List<Course> {
    return queryGateway
      .query("findCourses", AllCoursesMarker, ResponseTypes.multipleInstancesOf(Course::class.java))
      .join()
  }

  fun <T> Optional<T>.toKotlin(): T? = if (this.isEmpty) {
    null
  } else {
    this.get()
  }

}
