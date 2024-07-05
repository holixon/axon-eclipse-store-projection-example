package io.holixon.example.university.global.support.eclipsestore

import org.eclipse.serializer.configuration.types.Configuration
import org.eclipse.store.afs.sql.types.SqlDataSourceProvider
import org.postgresql.ds.PGSimpleDataSource
import javax.sql.DataSource

class SpringSqlDataSourceProvider : SqlDataSourceProvider {
  override fun provideDataSource(configuration: Configuration): DataSource {
    val dataSource = PGSimpleDataSource()

    with(dataSource) {
      setURL(configuration.get("url"))
      user = configuration.get("user")
      password = configuration.get("password")

    }
    return dataSource
  }
}
