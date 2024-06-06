package io.holixon.example.university.course.infrastructure.adapter.`in`.rest

import io.holixon.example.university.course.application.port.`in`.SubscribeToCourseInPort
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/course-subscriptions")
class CourseSubscriptionController(
  val subscribeToCourseInPort: SubscribeToCourseInPort
) {

  @PutMapping
  fun create(dto: SubscribeToCourseDto): ResponseEntity<Void> {
    subscribeToCourseInPort.subscribe(courseId = dto.courseId, matriculationNumber = dto.matriculationNumber)
    return noContent().build()
  }

  data class SubscribeToCourseDto(
    val courseId: String,
    val matriculationNumber: String
  )

}
