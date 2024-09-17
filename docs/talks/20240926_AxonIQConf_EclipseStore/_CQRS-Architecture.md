<!-- slide template="[[tpl-col-1-center]]" -->

::: title
Axon Framework Projections
:::

- Aggregates emit events
- Projectors use tracking processors / token store to receive events
- Projectors create query-optimized read models
- Read models are used by query handlers to answer queries
--

<!-- slide template="[[tpl-col-1-center]]" -->

::: title
Code example
:::

```kotlin[]
@Component
@ProcessingGroup("courses")
class CourseProjector(
	val courseRepository: CourseRepository
){

	@EventHandler  
	fun on(event: CourseCreatedEvent) {
		courseRepository.save(
			Course.fromEvent(event)
		)
	}
}
```

```kotlin[]
@Component
class CourseProjection(
	val courseRepository: CourseRepository
) {
	@QueryHandler(queryName = "findCourses")  
	fun findCourses(query: AllCoursesMarker): MutableList<Course> {  
	  return courseRepository.findAll().toMutableList()  
	}
}
```

--
<!-- slide template="[[tpl-col-1-center]]" -->

::: title
Implementation details
:::

* Projector and Projection share repository / storage
* Token store is used to track the position in event stream
* Token store uses the same storage as repository
* In memory:
	* Direct access to read model 
	* Need to replay the entire event stream on restart
* Persistent:
	* Need special mapping to entities / document / etc
	* Can restore state without replay
