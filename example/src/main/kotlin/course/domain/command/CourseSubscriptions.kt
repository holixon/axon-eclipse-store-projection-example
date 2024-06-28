package io.holixon.example.university.course.domain.command

data class CourseSubscriptions(
  private val subscriptions: List<String> = listOf()
) {
  fun hasStudent(matriculationNumber: String) = subscriptions.any { it == matriculationNumber }
  fun addStudent(matriculationNumber: String) = this.copy(subscriptions = subscriptions + matriculationNumber)
  fun removeStudent(matriculationNumber: String) = this.copy(subscriptions = subscriptions - matriculationNumber)

}
