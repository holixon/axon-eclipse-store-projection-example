#!/usr/bin/env bash
VERSION=1.1.0
case $1 in
  get)
  ./mvnw dependency:copy "-Dartifact=org.eclipse.store:storage-restclient-app:$VERSION:jar" -Dtransitive=false -ntp
  ;;
  run)
  java -jar "target/rest-client-app/storage-restclient-app-$VERSION.jar" --server.port=8027
  ;;
  *)
  echo "Usage: $0 get | run"
  ;;
esac

