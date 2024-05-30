package io.holixon.example.university.course.application.port.`in`

/**
 * Admin endpoint.
 */
interface ResetProjectionAdminInPort {
  /**
   * Resets data from the projection and triggers a replay.
   */
  fun resetCourseProjection()
}
