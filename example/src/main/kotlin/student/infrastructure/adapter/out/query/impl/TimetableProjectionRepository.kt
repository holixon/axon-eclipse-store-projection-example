package io.holixon.example.university.student.infrastructure.adapter.out.query.impl

import io.holixon.axon.eclipsestore.repository.ReadOnlyRepository
import io.holixon.example.university.student.domain.query.Timetable

interface TimetableProjectionRepository : ReadOnlyRepository<String, Timetable>
