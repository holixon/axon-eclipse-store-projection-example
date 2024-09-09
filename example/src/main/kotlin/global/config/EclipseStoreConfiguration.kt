package io.holixon.example.university.global.config

import io.holixon.axon.eclipsestore.root.ScanningStorageManagerConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import


@Configuration
@Import(ScanningStorageManagerConfiguration::class)
class EclipseStoreConfiguration {

}
