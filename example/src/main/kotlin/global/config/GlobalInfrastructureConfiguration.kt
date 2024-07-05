package io.holixon.example.university.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock
import java.time.ZoneId

@Configuration
class GlobalInfrastructureConfiguration {
  @Bean
  fun systemClock(): Clock = Clock.system(ZoneId.of("Europe/Berlin"))
}
