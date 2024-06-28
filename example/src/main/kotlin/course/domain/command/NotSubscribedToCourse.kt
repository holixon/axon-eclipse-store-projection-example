package io.holixon.example.university.course.domain.command

class NotSubscribedToCourse(courseName: String, matriculationNumber: String) :
  IllegalArgumentException("Student $matriculationNumber is not subscribed to course $courseName")
