
<!-- slide template="[[tpl-col-1-center]]" -->

::: title
 Whats my problem
:::
 
- Relational Projections are too complex:
	- Entities: Domain Model - Entity Graph - ORM - Relational model
	- Types: Domain - Java - JPQL - SQL - RDBMS
	- Queries: Domain - JPQL - SQL
- OO/Relational impedance mismatch
- The projector is the only writer, projection the only reader

:::

::: right

### What's the problem?
	
- Error prone
- high development costs (development and testing)
- high runtime costs
- high operational costs

:::

--

<!-- slide template="[[tpl-col-1-1]]" -->

::: title
What we need?
:::

::: left

### Functional

- Serialize data
- Store data
- Load data
- Deserialize data

:::

::: right

### Non-functional

- Backup Storage
- Recover Storage
- Smart replay

:::


--

<!-- slide template="[[tpl-col-1-1]]" -->

::: title
Eclipse Store
:::

::: left

### Approach

- Provide a serializer for almost any object model
	- assign object ids to anything, support most data collections
	- support custom type serialization
	- support migrations
	- works well on immutable data structures
- Provide abstract file system for storage
- Implement adapters for real storage technologies (Disk, DB, Cloud)
- Works well with Kotlin Data Classes, Value Classes
:::


::: right

### Project details

- Started 2017 as FOSS
- Microstream as main driving force
- Company from Regensburg, Germany
- Current version 1.4.0
- About 5-7 devs
- Became Eclipse project
- Offer professional support

:::


--

<!-- slide template="[[tpl-col-1-1]]" -->

::: title
How to use it
:::

::: left

### Configuration

- `EclipseStoreProperties` hold the universal configuration
- Create a `StorageManagerFoundation` and then create `StorageManager`
- Storage technology is just a configuration and has no impact on programming:
	- File
	- SQL Blobs
	- Cloud storages
:::

::: right

### Runtime

- Start with `StorageManager`
- Set Root Object
- Everything else should be navigatable from the Root
- No SQL, no JPQL, just Java / Kotlin
:::

--

<!-- slide template="[[tpl-col-1-center]]" -->

::: title
Please wake me up, or where are the mosquittos?
:::

- Only one Storage Manager per storage (no shared storage)
- Threading synchronization must be solved manually
- Locking must be solved manually

--
<!-- slide template="[[tpl-col-1-center]]" -->

::: title
Show me code
:::

### Root

```kotlin[]
data class MyRoot(
	val mySpecificField: String
)
``` 

### Write data

```kotlin[3]
fun writeData(storageManager: StorageManager, value: String) {

	val root = storageManager.getRoot()
	root.mySpecificField = value
	storageManager.store(root)
}
```

### Read data

```kotlin[3]
fun readData(storageManager: StorageManager): String {

	val root = storageManager.getRoot() as MyRoot
	return root.mySpecificField
}
```

--

<!-- slide template="[[tpl-col-1-center]]" -->

::: title
Lazy loading
:::

* Write operations are always eager
* Read operations are eager by default, but can be performed lazily:

```kotlin[]
data class MyRoot(
	val mySpecificField: Lazy<List<String>>
	val lazyValueMap: Map<String, Lazy<HugeBlobThing>>
)

fun readLazy(storageManager: StorageManager) {

	val root = storageManager.getRoot()
	val blob = root.lazyValueMap["key"]?.value
}

```

--

<!-- slide template="[[tpl-col-1-center]]" -->

::: title
Atomic operations
:::

```kotlin[]
fun multiple(storageManager: StorageManager, value1: Any, value2: String) {
	
	storageManager.storeAll(value1, value2)
}
```

```kotlin[]
fun usingStorer(storageManager: StorageManager, value1: Any, value2: String) {
	
	val storer = storageManager.getStorer()
	storer.store(value1)
	storer.store(value2)
	storer.commit()
}
```