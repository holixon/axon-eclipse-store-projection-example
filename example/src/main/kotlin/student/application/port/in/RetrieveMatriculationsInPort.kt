package io.holixon.example.university.student.application.port.`in`

import io.holixon.example.university.student.domain.query.Matriculation

/**
 * Query use case to retrieve information about registered students.
 */
interface RetrieveMatriculationsInPort {

  /**
   * Finds a student registration by matriculation number.
   * @param matriculationNumber matriculation number.
   * @return valida student registration or null.
   */
  fun findByNumber(matriculationNumber: String): Matriculation?

  /**
   * Lists all registered students.
   * @return list of matriculations.
   */
  fun findAll(): List<Matriculation>
}
