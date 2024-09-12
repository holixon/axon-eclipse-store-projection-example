package io.holixon.example.university.global.config

import io.holixon.axon.eclipsestore.root.StorageRootConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import


@Configuration
@Import(StorageRootConfiguration::class)
class EclipseStoreConfiguration {

}
