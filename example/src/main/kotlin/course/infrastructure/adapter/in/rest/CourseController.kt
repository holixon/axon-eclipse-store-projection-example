package io.holixon.example.university.course.infrastructure.adapter.`in`.rest

import io.holixon.example.university.course.application.port.`in`.CreateCourseInPort
import io.holixon.example.university.course.application.port.`in`.ModifyCourseInPort
import mu.KLogging
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.created
import org.springframework.http.ResponseEntity.noContent
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/courses")
class CourseController(
  private val createCourseInPort: CreateCourseInPort,
  private val modifyCourseInPort: ModifyCourseInPort,
) {
  companion object : KLogging()

  @PutMapping
  fun create(dto: CourseCreationDto): ResponseEntity<Void> {
    val id = UUID.randomUUID().toString()
    createCourseInPort.createCourse(id = id, name = dto.name, start = LocalDate.parse(dto.start), end = LocalDate.parse(dto.end), maxStudents = dto.maxStudents)
    return created(URI.create(id)).build()
  }


  @PostMapping("/{id}/capacity")
  fun changeCapacity(@PathVariable("id") id: String, maxStudents: Int): ResponseEntity<Void> {
    modifyCourseInPort.changeNumberOfStudents(id = id, maxStudents = maxStudents)
    return noContent().build()
  }

  data class CourseCreationDto(val name: String, val start: String, val end: String, val maxStudents: Int)

}
