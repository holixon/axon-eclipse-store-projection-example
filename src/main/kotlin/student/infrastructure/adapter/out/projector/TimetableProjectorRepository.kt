package io.holixon.example.university.student.infrastructure.adapter.out.projector

import io.holixon.example.university.course.infrastructure.support.eclipsestore.FullAccessRepository
import io.holixon.example.university.student.domain.query.Timetable

interface TimetableProjectorRepository : FullAccessRepository<String, Timetable>
