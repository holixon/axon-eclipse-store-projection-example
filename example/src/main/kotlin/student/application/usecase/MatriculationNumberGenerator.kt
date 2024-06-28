package io.holixon.example.university.student.application.usecase

import io.holixon.example.university.student.application.port.out.MatriculationNumberGeneratorOutPort
import org.springframework.stereotype.Component
import java.util.*

@Component
class MatriculationNumberGenerator : MatriculationNumberGeneratorOutPort {

  override fun generateMatriculationNumber(): String = UUID.randomUUID().toString()
}
