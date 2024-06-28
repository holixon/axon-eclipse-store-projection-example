package io.holixon.example.university.student.infrastructure.adapter.out.query.impl

import io.holixon.example.university.course.infrastructure.support.eclipsestore.ReadOnlyRepository
import io.holixon.example.university.student.domain.query.Matriculation

interface MatriculationProjectionRepository : ReadOnlyRepository<String, Matriculation>
