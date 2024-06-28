package io.holixon.example.university.student.infrastructure.adapter.out.query.impl

import io.holixon.example.university.course.infrastructure.support.eclipsestore.ReadOnlyRepository
import io.holixon.example.university.student.domain.query.Timetable

interface TimetableProjectionRepository : ReadOnlyRepository <String, Timetable>
