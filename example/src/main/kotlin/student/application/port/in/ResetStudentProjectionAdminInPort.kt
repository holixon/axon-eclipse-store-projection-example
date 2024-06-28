package io.holixon.example.university.student.application.port.`in`

/**
 * Administration use case to reset the projection.
 */
interface ResetStudentProjectionAdminInPort {
  /**
   * Resets student projection.
   */
  fun resetStudentProjection()

  /**
   * Resets timetable projection.
   */
  fun resetTimetableProjection()
}
