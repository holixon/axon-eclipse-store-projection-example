package io.holixon.example.university.course.domain.command

/**
 * Indicates impossible capacity change.
 */
class IllegalCapacityChange(msg: String) : IllegalArgumentException(msg)
