package io.holixon.example.university.student.domain.command

/**
 * Student is unknown.
 */
class UnknownStudent(matriculationId: String)
  : IllegalArgumentException("Student with $matriculationId doesn't exist.")
