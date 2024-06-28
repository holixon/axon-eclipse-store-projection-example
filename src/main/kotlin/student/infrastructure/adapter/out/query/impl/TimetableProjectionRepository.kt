package io.holixon.example.university.student.infrastructure.adapter.out.query.impl

import io.holixon.example.university.student.domain.query.Timetable

interface TimetableProjectionRepository {
  fun findByIdOrNull(matriculationNumber: String): Timetable?
}
