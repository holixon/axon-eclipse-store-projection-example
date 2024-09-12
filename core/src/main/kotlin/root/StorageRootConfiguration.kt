package io.holixon.axon.eclipsestore.root

import mu.KLogging
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan
class StorageRootConfiguration {
  companion object : KLogging()
}
