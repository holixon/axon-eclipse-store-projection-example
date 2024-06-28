package io.holixon.example.university.course.infrastructure.adapter.`in`.rest

import io.holixon.example.university.course.application.port.`in`.SubscribeToCourseInPort
import io.holixon.example.university.course.application.port.`in`.UnsubscribeFromCourseInPort
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/course-subscriptions")
class CourseSubscriptionController(
  val subscribeToCourseInPort: SubscribeToCourseInPort,
  val unsubscribeFromCourseInPort: UnsubscribeFromCourseInPort
) {

  @PutMapping
  fun create(dto: SubscribeToCourseDto): ResponseEntity<Void> {
    subscribeToCourseInPort.subscribe(courseId = dto.courseId, matriculationNumber = dto.matriculationNumber)
    return noContent().build()
  }

  @DeleteMapping("/{courseId}/{matriculationNumber}")
  fun remove(@PathVariable("courseId") courseId: String, @PathVariable("matriculationNumber") matriculationNumber: String): ResponseEntity<Void> {
    unsubscribeFromCourseInPort.unsubscribe(courseId = courseId, matriculationNumber = matriculationNumber)
    return noContent().build()
  }

  data class SubscribeToCourseDto(
    val courseId: String,
    val matriculationNumber: String
  )

}
