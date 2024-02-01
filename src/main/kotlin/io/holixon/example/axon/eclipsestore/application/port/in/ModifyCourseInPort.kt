package io.holixon.example.axon.eclipsestore.application.port.`in`

import java.util.concurrent.CompletableFuture

interface ModifyCourseInPort {
  fun changeNumberOfStudents(id: String, maxStudents: Int): CompletableFuture<Void>
}
