package io.holixon.example.university.student.infrastructure.adapter.out.projector

import io.holixon.example.university.student.domain.query.Timetable

interface TimetableProjectorRepository {
  fun findByIdOrNull(id: String): Timetable?
  fun save(value: Timetable): Timetable
  fun deleteById(id: String): Timetable?
  fun deleteAll()
  fun countAll(): Int

}
