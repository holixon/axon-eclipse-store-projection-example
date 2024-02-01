package io.holixon.example.axon.eclipsestore.adapter.out.command.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class ChangeCourseCapacityCommand(
  @TargetAggregateIdentifier
  val id: String,
  val maxStudents: Int
)
