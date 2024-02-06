package io.holixon.example.axon.eclipsestore

import io.holixon.example.axon.eclipsestore.domain.query.Course
import org.assertj.core.api.Assertions.assertThat
import org.eclipse.serializer.Serializer
import org.eclipse.serializer.SerializerFoundation
import org.eclipse.serializer.TypedSerializer
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

internal class EclipseSerializerTest {

  data class CourseWithTypedValues(
    val name: CourseName,
    val maxCap: Capacity
  )

  data class CourseWithTypedValuesV2(
    val maxCapacity: Capacity,
    val courseName: CourseName,
  )

  @JvmInline
  value class CourseName(val value: String)
  @JvmInline
  value class Capacity(val value: Int)

  @Test
  fun serialize_and_deserialize_using_typed_serializer() {

    val course = Course(
      id = UUID.randomUUID().toString(),
      name = "Math I",
      start = LocalDate.parse("2024-01-01"),
      end = LocalDate.parse("2024-06-15"),
      maxCapacity = 17
    )


  // use typed serializer to store data into bytes
    val writer = TypedSerializer.Bytes(
      SerializerFoundation.New()
    )
    val serialized = writer.serialize(course)


    val reader = TypedSerializer.Bytes(
      SerializerFoundation.New()
    )

    val deserialized: Course = reader.deserialize(serialized)

    assertThat(deserialized).isEqualTo(course)
  }

  @Test
  fun serialize_and_deserialize_with_value_using_typed_serializer() {

    val course = CourseWithTypedValues(
      name = CourseName("Math I"),
      maxCap = Capacity(17)
    )


    // use typed serializer to store data into bytes
    val writer = TypedSerializer.Bytes(
      SerializerFoundation.New()
    )
    val serialized = writer.serialize(course)


    val reader = TypedSerializer.Bytes(
      SerializerFoundation.New()
    )

    val deserialized: CourseWithTypedValues = reader.deserialize(serialized)

    assertThat(deserialized).isEqualTo(course)
  }


  @Test
  fun serialize_and_deserialize_with_changed_props() {

    val course = CourseWithTypedValues(
      name = CourseName("Math I"),
      maxCap = Capacity(17)
    )

    val writer = Serializer.Bytes(
      SerializerFoundation.New()
    )
    val serialized = writer.serialize(course)


    val reader = Serializer.Bytes(
      SerializerFoundation.New().registerEntityTypes(CourseWithTypedValuesV2::class.java)
    )

    val deserialized: CourseWithTypedValuesV2 = reader.deserialize(serialized)

    assertThat(deserialized.courseName).isEqualTo(course.name)
    assertThat(deserialized.maxCapacity).isEqualTo(course.maxCap)
  }

}
