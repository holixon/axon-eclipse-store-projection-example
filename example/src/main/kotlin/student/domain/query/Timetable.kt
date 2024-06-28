package io.holixon.example.university.student.domain.query

data class Timetable(
  val matriculationNumber: String,
  val courses: List<String> = listOf()
) {
  fun addCourse(courseId: String): Timetable = this.copy(courses = courses + courseId)
  fun removeCourse(courseId: String): Timetable = this.copy(courses = courses - courseId)
}
