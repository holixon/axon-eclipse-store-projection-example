package io.holixon.example.university.course.application.port.`in`

import io.holixon.example.university.course.domain.query.Course

/**
 * Course information retrieval.
 */
interface RetrieveCoursesInPort {
  /**
   * Searches for course with id.
   * @param id course id.
   * @return course if found.
   */
  fun getCourseById(id: String): Course?

  /**
   * Retrieves the list of all courses.
   * @return list of all courses in the system.
   */
  fun getAllCourses(): List<Course>
}
