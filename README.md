# Axon Eclipse Store Projection Example

[![example](https://img.shields.io/badge/lifecycle-EXAMPLE-blue.svg)](https://github.com/holisticon#open-source-lifecycle)
[![Build Status](https://github.com/holixon/axon-eclipse-store-projection-example/workflows/Development%20branches/badge.svg)](https://github.com/holixon/axon-eclipse-store-projection-example/actions)
[![sponsored](https://img.shields.io/badge/sponsoredBy-Holisticon-RED.svg)](https://holisticon.de/)

## Motivation

Building projections in CQRS/ES is easy using Java / Kotlin classes. An in-memory projections are fast, easy to-test and easy-to-debug, but they 
need a replay to be refilled on application restarts. On the other hand, building JPA projection allow to store data in RDBMS, but require 
a mapping between the in-memory object representation and relational schema. 

[Microstream](https://microstream.one/) [EclipseStore](https://docs.eclipsestore.io/manual/intro/welcome.html) allows to easily store object structure without the object-relational impedance mismatch. This project is an example showing its application.

## Features and ideas
 
- Created a "generic" StorageRoot to be able to put elements referenced by name inside (eager loaded)
- Created Axon Framework Token-Store using Eclipse Store as persistence
- Provided `ReadOnlyRepository<KEY, VALUE>` and `FullAccessRepository<KEY, VALUE>` repositories
- Provided map-based implementation for the repositories with eager loading

## Implemented architecture

- Implemented a multi-bounded-context modulith
- Followed Clean Architecture approach (Onion + Hex)
- Used Axon Framework as a part of in/outbound adapter
- Followed CQRS/ES style in those adapters
- Used Axon Server as event store
- Used Eclipse-Store as the ONLY projection persistence technology
- PostgreSQL is available as backing storage for Eclipse-Store (can be switched to file-based) and for other persistence (aka Saga store)

## Implemented use case

The application is a virtual university. It contains of two main sub-domains: course and student. The course domain is about creation of courses taught in the university.
Every course has a name, a time frame when it is offered and the maximum and current number of students. The student domain is about students. They
can register in the university for certain time frame. A student has some personal data, time frame of registration and gets an matriculation number.

Now students can subscribe to courses. There are (on-purpose) some business rules checked:

- a student can only subscribe if the maximum number of students is not reached in the course
- a student can only subscribe if the course has not yet started
- on the course side, it is sufficient to provide the matriculation number of the student to subscribe
- on the stundet side, the course must take place during the registration time frame of the student (you must be in the university to subscribe to the course).

## How to run

- Start Axon Server / PostgreSQL via `docker-compose up`
- Build application with mvn (`./mvnw clean package`)
- Run `EclipseStoreProjectingApplication` with Spring profile `disk` to use disk-based Eclipse Store
- Run `EclipseStoreProjectingApplication` with Spring profile `postgresql` to use DB-based Eclipse Store
- Open Swagger UI: [http://localhost:8091/swagger-ui/index.html] and click.

## Running scenarios 

1. In order to automate testing and demonstration, some scenarios are created. To run them, start the application first (as described above).
2. Run `registration.http` using IDEA IntelliJ http client (This will create some courses in seasons and two students)
3. Run `subscription-happy.http` using IDEA IntelliJ http client (This will register "Miss Piggy" for "Physics II")
4. Run `subscription-wrong.http` using IDEA IntelliJ http client (This will try to register "Kermit The Frog" for "Nuclear Power I", 
but will fail and then compensate, because Kermit is not in the university as the course is given.)

## Next steps

- TODO: Create a multi-instance setup of the application with synchronized Eclipse-Store for the projections.
- TODO: provide a lazy-loading map-based repository implementation

## Feedback

If you find this interesting, please provide me some feedback...

