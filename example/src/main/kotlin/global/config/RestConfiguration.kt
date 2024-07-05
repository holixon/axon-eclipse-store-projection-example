package io.holixon.example.university.global.config

import io.holixon.example.university.global.support.rest.ApiErrorDto
import org.axonframework.modelling.command.AggregateNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class RestConfiguration {

  @ExceptionHandler(
    IllegalArgumentException::class,
    AggregateNotFoundException::class
  )
  fun badRequest(exception: Exception): ResponseEntity<ApiErrorDto> {
    return ResponseEntity.badRequest().body(
      ApiErrorDto(listOf(exception.message ?: ""))
    )
  }

}
