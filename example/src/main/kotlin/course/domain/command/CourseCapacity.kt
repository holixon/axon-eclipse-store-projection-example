package io.holixon.example.university.course.domain.command

data class CourseCapacity(
  val maxStudents: Int,
  val currentStudents: Int = 0
) {

  fun allowChangeCapacity(maxStudents: Int): Result<CourseCapacity> {
    return if (maxStudents >= currentStudents) {
      Result.success(this.copy(maxStudents = maxStudents, currentStudents = this.currentStudents))
    } else {
      Result.failure(IllegalCapacityChange("Can't set capacity to less students as already in course."))
    }
  }

  fun changeCapacity(maxStudents: Int): CourseCapacity = this.copy(maxStudents = maxStudents)


  fun isFull(): Boolean = currentStudents == maxStudents
  fun addStudent(): CourseCapacity = this.copy(currentStudents = currentStudents + 1)
  fun removeStudent(): CourseCapacity = this.copy(currentStudents = currentStudents - 1)

}
