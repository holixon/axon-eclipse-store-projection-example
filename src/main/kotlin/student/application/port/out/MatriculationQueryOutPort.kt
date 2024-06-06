package io.holixon.example.university.student.application.port.out

import io.holixon.example.university.student.domain.query.Matriculation
import java.util.concurrent.CompletableFuture

interface MatriculationQueryOutPort {

  fun findMatriculation(matriculationNumber: String): CompletableFuture<Matriculation?>

  fun findAllMatriculations(): CompletableFuture<List<Matriculation>>
}
