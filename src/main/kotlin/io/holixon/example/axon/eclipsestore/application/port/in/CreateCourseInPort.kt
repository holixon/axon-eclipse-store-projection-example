package io.holixon.example.axon.eclipsestore.application.port.`in`

import java.time.LocalDate
import java.util.concurrent.CompletableFuture

interface CreateCourseInPort {
  fun createCourse(id: String, name: String, maxStudents: Int, start: LocalDate, end: LocalDate): CompletableFuture<Void>
}
