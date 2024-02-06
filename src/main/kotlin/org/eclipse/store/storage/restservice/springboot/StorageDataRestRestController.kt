package org.eclipse.store.storage.restservice.springboot

import com.google.gson.*
import jakarta.annotation.PostConstruct
import mu.KLogging
import org.eclipse.store.storage.restadapter.types.StorageRestAdapter
import org.eclipse.store.storage.restadapter.types.ViewerObjectDescription
import org.eclipse.store.storage.restservice.types.StorageRestService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/store-data")
class StorageDataRestRestController(
  private val adapter: StorageRestAdapter
) : StorageRestService {

  private lateinit var gson: Gson

  companion object : KLogging()

  @PostConstruct
  override fun start() {
    logger.info { "[Eclipse Store REST Service]: Starting Spring REST service." }
    val serializerDate = JsonSerializer<Date> { src, _, _ -> JsonPrimitive(src.toInstant().toString()) }

    this.gson = GsonBuilder()
      .setLongSerializationPolicy(LongSerializationPolicy.STRING)
      .serializeNulls()
      .registerTypeAdapter(Date::class.java, serializerDate)
      .create()

  }

  override fun stop() {
    logger.info { "[Eclipse Store REST Service]: Shutting down REST service." }
  }

  @GetMapping("/", produces = [MediaType.APPLICATION_JSON_VALUE])
  fun getAvailableRoutes(): ResponseEntity<String> {
    val defaultMethods = arrayOf("get")
    return ok(
      gson.toJson(
        listOf(
          RouteWithMethodDto("/", defaultMethods),
          RouteWithMethodDto("/root", defaultMethods),
          RouteWithMethodDto("/dictionary", defaultMethods),
          RouteWithMethodDto("/object/:oid", defaultMethods),
          RouteWithMethodDto("/maintenance/filesStatistics", defaultMethods)
        )
      )
    )
  }


  @GetMapping("/root", produces = [MediaType.APPLICATION_JSON_VALUE])
  fun getRoot(): ResponseEntity<String> {
    return ok(gson.toJson(adapter.userRoot))
  }

  @GetMapping("/dictionary", produces = [MediaType.APPLICATION_JSON_VALUE])
  fun getTypeDictionary(): ResponseEntity<String> {
    return ok(adapter.typeDictionary)
  }

  @GetMapping("/object/{oid}", produces = [MediaType.APPLICATION_JSON_VALUE])
  fun getObject(
    @PathVariable("oid") objectId: Long,
    @RequestParam("valueLength", required = false) valueLength: Long?,
    @RequestParam("fixedOffset", required = false) fixedOffset: Long?,
    @RequestParam("fixedLength", required = false) fixedLength: Long?,
    @RequestParam("variableOffset", required = false) variableOffset: Long?,
    @RequestParam("variableLength", required = false) variableLength: Long?,
    @RequestParam("references", required = false) resolveReferences: Boolean?,
  ): ResponseEntity<String> {
    val storageObject: ViewerObjectDescription = adapter.getObject(
      objectId,
      fixedOffset ?: 0L,
      fixedLength ?: Long.MAX_VALUE,
      variableOffset ?: 0L,
      variableLength ?: Long.MAX_VALUE,
      valueLength ?: Long.MAX_VALUE,
      resolveReferences ?: false
    )
    return ok(gson.toJson(storageObject))
  }

  @GetMapping("/maintenance/filesStatistics", produces = [MediaType.APPLICATION_JSON_VALUE])
  fun getFileStatistics(): ResponseEntity<String> {
    return ok(gson.toJson(adapter.storageFilesStatistics))
  }


  @Suppress("ArrayInDataClass", "PropertyName")
  data class RouteWithMethodDto(
    val URL: String,
    val HttpMethod: Array<String>
  )
}
