package io.holixon.example.university.student.application.port.out

import io.holixon.example.university.student.domain.query.Timetable

interface TimetableOutPort {
  fun getTimetableByMatriculationNumber(matriculationNumber: String): Timetable?
}
