package io.holixon.example.university.course.infrastructure.adapter.`in`.rest

import io.holixon.example.university.course.application.port.`in`.SubscribeToCourseInPort
import io.holixon.example.university.course.application.port.`in`.UnsubscribeFromCourseInPort
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.web.bind.annotation.*
import javax.annotation.Nonnull

@RestController
@RequestMapping("/course-subscriptions")
class CourseSubscriptionController(
  val subscribeToCourseInPort: SubscribeToCourseInPort,
  val unsubscribeFromCourseInPort: UnsubscribeFromCourseInPort
) {

  @PutMapping
  fun create(@Valid @RequestBody @Nonnull dto: SubscribeToCourseDto): ResponseEntity<Void> {
    subscribeToCourseInPort.subscribe(courseId = dto.courseId, matriculationNumber = dto.matriculationNumber).join()
    return noContent().build()
  }

  @DeleteMapping("/{courseId}/{matriculationNumber}")
  fun remove(@PathVariable("courseId") @Nonnull courseId: String, @PathVariable("matriculationNumber") @Nonnull matriculationNumber: String): ResponseEntity<Void> {
    unsubscribeFromCourseInPort.unsubscribe(courseId = courseId, matriculationNumber = matriculationNumber)
    return noContent().build()
  }

  data class SubscribeToCourseDto(
    @Nonnull
    val courseId: String,
    @Nonnull
    val matriculationNumber: String
  )

}
