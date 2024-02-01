package io.holixon.example.axon.eclipsestore.adapter.`in`.rest

import io.holixon.example.axon.eclipsestore.application.port.`in`.CreateCourseInPort
import io.holixon.example.axon.eclipsestore.application.port.`in`.ModifyCourseInPort
import io.holixon.example.axon.eclipsestore.application.port.`in`.RetrieveCoursesInPort
import io.holixon.example.axon.eclipsestore.domain.query.Course
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/courses")
class CourseController(
  private val createCourseInPort: CreateCourseInPort,
  private val modifyCourseInPort: ModifyCourseInPort,
  private val retrieveCoursesInPort: RetrieveCoursesInPort
) {

  @GetMapping
  fun getCourses(): ResponseEntity<List<CourseDto>> {
    return ok(retrieveCoursesInPort.getAllCourses().map { it.toDto() })
  }

  @GetMapping("/{id}")
  fun getCourseById(@PathVariable("id") id: String): ResponseEntity<CourseDto> {
    return ok(retrieveCoursesInPort.getCourseById(id)?.toDto())
  }

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

  data class CourseDto(val id: String, val name: String, val start: String, val end: String, val maxStudents: Int)
  data class CourseCreationDto(val name: String, val start: String, val end: String, val maxStudents: Int)

  fun Course.toDto() = CourseDto(
    id = this.id,
    name = this.name,
    start = this.start.toString(),
    end = this.end.toString(),
    maxStudents = this.maxCapacity
  )
}
