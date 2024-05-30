package io.holixon.example.university

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

fun main(args: Array<String>) {
  System.setProperty("disable-axoniq-console-message", "true")
  runApplication<EclipseStoreProjectingApplication>(*args).let { Unit }
}

@SpringBootApplication
class EclipseStoreProjectingApplication
