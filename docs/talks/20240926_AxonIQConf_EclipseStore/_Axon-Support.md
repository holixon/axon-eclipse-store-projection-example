<!-- slide template="[[tpl-col-1-center]]" -->

::: title
Axon Support
:::

- Event Projector and Projection share a repository abstraction to work at
- TokenStore for Tracking Processor to allow replay (Projector side)
- In-Memory model, using EclipseStore for backing 
- Every node has its own store (not shared)* 
- Repository per read model type (`VALUE`) with a typed identity (`KEY`)

--

<!-- slide template="[[tpl-col-1-center]]" -->

::: title
Root, Repository
:::

```kotlin
class StorageRoot {
	private val elements: ConcurrentHashMap<String, Any> = ConcurrentHashMap()
}
```


```kotlin

interface FullAccessRepository<KEY : Any, VALUE : Any> {  
  fun findById(id: KEY): VALUE  
  fun findByIdOrNull(id: KEY): VALUE?  
  fun findAll(): List<VALUE>  
  fun countAll(): Int  
  fun save(value: VALUE): VALUE  
  fun deleteAll()  
  fun deleteById(id: KEY): VALUE?
}
```


--

<!-- slide template="[[tpl-col-1-center]]" -->

::: title
Abstract for manual implementation
:::

Instantiate using type hierarchy:

```kotlin 

class CourseProjectorRepositoryImpl(storageRootSupplier: () -> StorageRoot) :  
  CourseProjectorRepository, 
  PersistentMapBasedRepository<String, Course>(  
    storageRootSupplier = storageRootSupplier,  
    config = EclipseStoreRepositoryConfig(name = "courses"),  
    idExtractor = { it.id }  
  )

```

--

<!-- slide template="[[tpl-col-1-center]]" -->

::: title
Dynamic Proxy
:::

Create dynamic CGLIB Proxies for implementation based on the interface definition.

```kotlin 

@EclipseStoreRepositoryConfiguration(
	storage = "courses",
	idExtractorHint = "id"
)
public interface CourseProjectorRepository : EclipseStoreRepository<String, Course>
```

--

<!-- slide template="[[tpl-col-1-1]]" -->

::: title
Failover
:::

::: left

### Problem

- if store is not shared, a new instance performs a full event replay

:::

::: right

### Solution

- if no storage present, ask for it (query)
- if another instance is present it might provide a backup snapshot
- restore the snapshot and replay from there
 
:::
