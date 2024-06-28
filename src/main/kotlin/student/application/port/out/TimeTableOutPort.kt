package io.holixon.example.university.student.application.port.out

import io.holixon.example.university.student.domain.query.Timetable
import java.util.*
import java.util.concurrent.CompletableFuture

interface TimeTableOutPort {
  fun getTableName(matriculationNumber: String): CompletableFuture<Optional<Timetable>>
}
