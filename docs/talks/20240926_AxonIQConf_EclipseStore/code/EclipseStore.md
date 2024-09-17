```kotlin

data class MyRoot(
	val mySpecificField: String,
)

```

```kotlin 

fun writeData(storageManager: StorageManager, value: String) {

	val root = storageManager.getRoot()
	root.mySpecificField = value
	storageManager.store(root)
}

fun readData(storageManager: StorageManager): String {

	val root = storageManager.getRoot() as MyRoot
	return root.mySpecificField
}


```
