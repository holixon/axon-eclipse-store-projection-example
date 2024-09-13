package io.holixon.example.university.global.config

import io.holixon.axon.eclipsestore.root.ProjectionSupportConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import java.time.Clock
import java.time.ZoneId

@Configuration
@Import(ProjectionSupportConfiguration::class)
class GlobalInfrastructureConfiguration {
  @Bean
  fun systemClock(): Clock = Clock.system(ZoneId.of("Europe/Berlin"))
}
