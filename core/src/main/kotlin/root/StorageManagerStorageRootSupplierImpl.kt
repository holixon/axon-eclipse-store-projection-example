package io.holixon.axon.eclipsestore.root

import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager

class StorageManagerStorageRootSupplierImpl(
  private val storageManager: EmbeddedStorageManager
) : StorageRootSupplier {

  lateinit var root: StorageRoot

  override fun invoke(): StorageRoot {
    synchronized(this) {
      if (!this::root.isInitialized) {
        this.root = StorageRoot.init(storageManager)
      }
    }
    return root
  }
}
