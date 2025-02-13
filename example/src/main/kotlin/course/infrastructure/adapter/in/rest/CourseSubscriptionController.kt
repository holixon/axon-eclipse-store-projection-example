package io.holixon.example.university.course.infrastructure.adapter.`in`.rest

import io.github.oshai.kotlinlogging.KotlinLogging
import io.holixon.example.university.course.application.port.`in`.SubscribeToCourseInPort
import io.holixon.example.university.course.application.port.`in`.UnsubscribeFromCourseInPort
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.web.bind.annotation.*
import javax.annotation.Nonnull

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/course-subscriptions")
class CourseSubscriptionController(
  val subscribeToCourseInPort: SubscribeToCourseInPort,
  val unsubscribeFromCourseInPort: UnsubscribeFromCourseInPort
) {

  @PutMapping
  fun create(@Valid @RequestBody @Nonnull dto: SubscribeToCourseDto): ResponseEntity<Void> {
    logger.info { "[COURSE-SUBSCRIPTION-REST]: Trying to create a new subscription for student ${dto.matriculationNumber} and course id ${dto.courseId}" }
    subscribeToCourseInPort.subscribe(courseId = dto.courseId, matriculationNumber = dto.matriculationNumber).join()
    return noContent().build()
  }

  @DeleteMapping("/{courseId}/{matriculationNumber}")
  fun remove(@PathVariable("courseId") @Nonnull courseId: String, @PathVariable("matriculationNumber") @Nonnull matriculationNumber: String): ResponseEntity<Void> {
    logger.info { "[COURSE-SUBSCRIPTION-REST]: Trying to delete subscription for student $matriculationNumber and course id $courseId" }
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
