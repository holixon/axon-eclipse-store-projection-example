package io.holixon.example.university.student.application.port.`in`

import io.holixon.example.university.student.domain.query.TimetableWithDetails


interface RetrieveStudentTimetableInPort {
  fun getTimetable(matriculationNumber: String): TimetableWithDetails?
}
