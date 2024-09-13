package io.holixon.axon.eclipsestore.root

import org.axonframework.queryhandling.QueryBus
import org.eclipse.store.integrations.spring.boot.types.factories.EmbeddedStorageFoundationFactory
import org.eclipse.store.integrations.spring.boot.types.factories.EmbeddedStorageManagerFactory
import org.springframework.stereotype.Component

@Component
class SingleInstanceStorageRootSupplier(
  private val foundationFactory: EmbeddedStorageFoundationFactory,
  private val managerFactory: EmbeddedStorageManagerFactory,
  private val queryBus: QueryBus,
  private val projectionSupportProperties: ProjectionSupportProperties
) : StorageRootSupplier {

  lateinit var root: StorageRoot

  override fun invoke(): StorageRoot {
    synchronized(this) {
      if (!this::root.isInitialized) {
        this.root = StorageRoot.init(
          projectionSupportProperties = projectionSupportProperties,
          queryBus = queryBus,
          managerFactory = managerFactory,
          foundationFactory = foundationFactory
        )
      }
    }
    return root
  }
}
