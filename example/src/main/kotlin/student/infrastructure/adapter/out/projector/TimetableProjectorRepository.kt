package io.holixon.example.university.student.infrastructure.adapter.out.projector

import io.holixon.axon.eclipsestore.repository.FullAccessRepository
import io.holixon.example.university.student.domain.query.Timetable

interface TimetableProjectorRepository : FullAccessRepository<String, Timetable>
