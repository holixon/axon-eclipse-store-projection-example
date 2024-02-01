package io.holixon.example.axon.eclipsestore.adapter.out.query.model

import io.holixon.example.axon.eclipsestore.domain.query.Course
import io.holixon.example.axon.eclipsestore.infrastructure.StorageRoot
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class CourseProjectionRepositoryImpl(
  private val storageRoot: StorageRoot
) : CourseProjectionRepository {

  companion object {
    const val COURSES = "courses"
  }

  @PostConstruct
  fun initialize() {
    if (!storageRoot.contains(COURSES)) {
      storageRoot.set(COURSES, mutableMapOf<String, Course>())
    }
  }

  override fun findById(id: String): Course? {
    return getCourses()[id]
  }

  override fun findAll(): List<Course> {
    return getCourses().values.toList()
  }


  override fun save(course: Course) {
    val courses = getCourses()
    courses[course.id] = course
    changeCourses(courses)
  }

  private fun getCourses(): MutableMap<String, Course> = storageRoot.get(COURSES)

  private fun changeCourses(courses: MutableMap<String, Course>) {
    storageRoot.append(courses)
  }
}
