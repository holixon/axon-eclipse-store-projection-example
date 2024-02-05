package io.holixon.example.axon.eclipsestore.application.port.out

import io.holixon.example.axon.eclipsestore.domain.query.Course

/**
 * Port to load courses.
 */
interface CoursesQueryOutPort {

  /**
   * Load courses by id.
   * @param id course id.
   * @return course if found or null.
   */
  fun getCourseById(id: String): Course?

  /**
   * Retrieves a list of courses.
   * @return list of courses.
   */
  fun getAllCourses(): List<Course>
}
