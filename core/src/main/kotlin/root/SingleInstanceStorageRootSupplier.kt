package io.holixon.axon.eclipsestore.root

import org.axonframework.queryhandling.QueryBus
import org.eclipse.store.integrations.spring.boot.types.configuration.EclipseStoreProperties
import org.eclipse.store.integrations.spring.boot.types.factories.EmbeddedStorageFoundationFactory
import org.eclipse.store.integrations.spring.boot.types.factories.EmbeddedStorageManagerFactory
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager
import org.springframework.stereotype.Component

@Component
class SingleInstanceStorageRootSupplier(
  private val foundationFactory: EmbeddedStorageFoundationFactory,
  private val managerFactory: EmbeddedStorageManagerFactory,
  private val queryBus: QueryBus,
  private val storeProjectionSupportProperties: StoreProjectionSupportProperties
) : StorageRootSupplier {

  lateinit var root: StorageRoot

  override fun invoke(): StorageRoot {
    synchronized(this) {
      if (!this::root.isInitialized) {
        this.root = StorageRoot.init(
          storeProjectionSupportProperties = storeProjectionSupportProperties,
          queryBus = queryBus,
          managerFactory = managerFactory,
          foundationFactory = foundationFactory
        )
      }
    }
    return root
  }
}
