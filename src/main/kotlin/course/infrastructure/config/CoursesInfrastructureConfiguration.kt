package io.holixon.example.university.course.infrastructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock
import java.time.ZoneId

@Configuration
class CoursesInfrastructureConfiguration {
  @Bean
  fun systemClock(): Clock = Clock.system(ZoneId.of("Europe/Berlin"))
}
