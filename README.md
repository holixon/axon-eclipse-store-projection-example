# Axon Eclipse Store Projection Example

[![incubating](https://img.shields.io/badge/lifecycle-INCUBATING-orange.svg)](https://github.com/holisticon#open-source-lifecycle)
[![Build Status](https://github.com/holixon/axon-eclipse-store-projection-example/workflows/Development%20branches/badge.svg)](https://github.com/holixon/axon-eclipse-store-projection-example/actions)
[![sponsored](https://img.shields.io/badge/sponsoredBy-Holisticon-RED.svg)](https://holisticon.de/)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.holixon.example.axon-eclipse-store-projection/axon-eclipse-store-projection-example/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.holixon.example.axon-event-transformation/axon-event-transformation-example)

## Motivation

Building projections in CQRS/ES is easy using Java / Kotlin classes. An in-memory projection is fast, easy to-test and easy-to-debug, but it 
needs a replay to be refilled on application restarts. On the other hand, building JPA projection allow to store data ina RDBMS, but require 
a mapping between the in-memory object representation and relational schema. Eclipse Store allows to easily store object structure without
the object-relational impedance mismatch. This project is an example showing its application.

## Additional POC / Ideas
- 
- Created a "generic" StorageRoot to be able to put any stuff inside
- The Courses Projection inserts courses into the root
- Its token store injects the tracking tokens (supplied small store implementation) 

## How to run

- Start Axon Server via `docker-compose up`
- Build application with mvn (`./mvnw clean package`)
- Run `EclipseStoreProjectingApplication`
- Open Swagger UI: http://localhost:8091/swagger-ui/index.html
- Open a schedule controller and create a schedule
- Restart application - retrieve courses via courses controller
- Pay attention to the logs - once the events are stored, they are not replayed afterwards - both the token store and the projection is persistent.
