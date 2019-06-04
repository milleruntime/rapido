# Rapido

A fast lightweight query app for Accumulo.

Rapido uses Spring Boot to create a self contained Jar that is deployed with Embedded Tomcat.

### Requirements:
Java 11

## To build & deploy locally

mvn clean package && java -jar target/rapido-*.jar

Go to http://localhost:8080/

### To Run an IT (Requires running Accumulo)

mvn clean test -Dtest=app.it.QueryControllerIT
