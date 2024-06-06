package io.holixon.example.university.course.domain.command

class AlreadySubscribedToCourse(courseName: String, matriculationNumber: String)
  : IllegalArgumentException("Student $matriculationNumber is already subscribed to course $courseName")
