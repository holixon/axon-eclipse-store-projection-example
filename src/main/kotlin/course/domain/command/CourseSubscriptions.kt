package io.holixon.example.university.course.domain.command

class CourseSubscriptions(
  private val subscriptions: MutableList<String> = mutableListOf()
) {
  fun hasStudent(matriculationNumber: String) = subscriptions.any { it == matriculationNumber }
  fun addStudent(matriculationNumber: String) = subscriptions.add(matriculationNumber)

}
