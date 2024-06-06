package io.holixon.example.university.course.domain.command

class NotSubscribedToCourse(courseId: String) : IllegalArgumentException("Not subscribed to course with id $courseId")
