package io.holixon.example.university.course.domain.command

data class CourseCapacity(
  val maxStudents: Int,
  val currentStudents: Int = 0
) {

  fun allowChangeCapacity(maxStudents: Int): Result<CourseCapacity> {
    return if (maxStudents >= currentStudents) {
      Result.success(this.copy(maxStudents = maxStudents, currentStudents = this.currentStudents))
    } else {
      Result.failure(IllegalCapacityChangeException("Can't set capacity to less students as already in course."))
    }
  }

  fun changeCapacity(maxStudents: Int): CourseCapacity = this.copy(maxStudents = maxStudents)

}
