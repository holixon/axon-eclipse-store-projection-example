package io.holixon.example.university.course.infrastructure.adapter.out.command.api

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDate

data class CreateCourseCommand(
  @TargetAggregateIdentifier
  val id: String,
  val name: String,
  val maxStudents: Int,
  val start: LocalDate,
  val end: LocalDate,
)
