package io.holixon.axon.eclipsestore.root

import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class StorageManagerStorageRootSupplierImpl(
  private val storageManager: EmbeddedStorageManager,
  @Lazy
  private val storageCopier: StorageCopier
) : StorageRootSupplier {

  lateinit var root: StorageRoot

  override fun invoke(): StorageRoot {
    synchronized(this) {
      if (!this::root.isInitialized) {
        this.root = StorageRoot.init(storageManager = storageManager, storageCopier = storageCopier)
      }
    }
    return root
  }
}
