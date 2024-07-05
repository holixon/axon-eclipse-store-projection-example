package io.holixon.example.university.global.support.rest

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

data class ApiErrorDto(
  @Schema(example = "null", required = true, description = "Single error description.")
  @get:JsonProperty("errors", required = true) val errors: List<String>
)
