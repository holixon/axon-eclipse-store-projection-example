package io.holixon.example.axon.eclipsestore.infrastructure

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

@Configuration
class JacksonSerializerConfiguration {

  @Bean
  fun mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter {
    return MappingJackson2HttpMessageConverter(
      Jackson2ObjectMapperBuilder()
        .featuresToDisable(
          SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS,
          SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
        )
        .modules(
          KotlinModule.Builder().build(), // kotlin data classes
          JavaTimeModule(),
          Jdk8Module() // Optional
        )
        .build()
    )
  }

  @Bean
  fun defaultAxonObjectMapper() = jacksonObjectMapper()
    .apply {
      registerModule(JavaTimeModule())
      enableDefaultTyping(ObjectMapper.DefaultTyping.NON_CONCRETE_AND_ARRAYS)
      enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
      enable(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)
      disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
    }
}
