package io.holixon.example.university.student.infrastructure.adapter.out.projector


import io.holixon.example.university.course.infrastructure.support.eclipsestore.FullAccessRepository
import io.holixon.example.university.student.domain.query.Matriculation

/**
 * Student repository.
 */
interface StudentProjectorRepository : FullAccessRepository<String, Matriculation>
