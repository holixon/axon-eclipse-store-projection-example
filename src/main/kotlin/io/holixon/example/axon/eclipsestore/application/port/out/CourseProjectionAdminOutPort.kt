package io.holixon.example.axon.eclipsestore.application.port.out

/**
 * Admin port to reset course projection.
 */
interface CourseProjectionAdminOutPort {
  /**
   * Resets the projection.
   */
  fun resetProjection()
}
