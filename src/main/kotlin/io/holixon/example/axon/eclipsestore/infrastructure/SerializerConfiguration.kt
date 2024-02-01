package io.holixon.example.axon.eclipsestore.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.axonframework.serialization.Serializer
import org.axonframework.serialization.json.JacksonSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class SerializerConfiguration {

  @Bean
  fun objectMapper() = jacksonObjectMapper()

  @Bean
  @Primary
  fun serializer(objectMapper: ObjectMapper): Serializer = JacksonSerializer.builder().objectMapper(objectMapper).lenientDeserialization().defaultTyping().build()
}
