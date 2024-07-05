package io.holixon.example.university.course.infrastructure.adapter.`in`.rest

import io.holixon.example.university.course.application.port.`in`.ResetCourseProjectionAdminInPort
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/course-admin/season")
class CourseAdminResetController(
  private val resetCourseProjectionAdminInPort: ResetCourseProjectionAdminInPort
) {

  @DeleteMapping
  fun reset(): ResponseEntity<Void> {
    resetCourseProjectionAdminInPort.resetCourseProjection()
    return noContent().build()
  }

}
