package io.holixon.example.university.student.infrastructure.adapter.out.projector


import io.holixon.axon.eclipsestore.repository.FullAccessRepository
import io.holixon.example.university.student.domain.query.Matriculation

/**
 * Student repository.
 */
interface StudentProjectorRepository : FullAccessRepository<String, Matriculation>
