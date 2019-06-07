# Rapido

A fast lightweight query app for [Accumulo].

Rapido uses Spring Boot to create a self contained Jar that is deployed with Embedded Tomcat.

### Requirements:
- Maven (only for building)
- Java 8+
- Running instance of [Accumulo]. For a quick and dirty cluster checkout [Uno].

## Build & Run

To build run:
```bash
mvn clean package
```
To Deploy locally:
```bash
java -jar target/rapido-*.jar
```
To do both in one command:
```bash
mvn clean package && java -jar target/rapido-*.jar
```

Go to http://localhost:8080/

### Configuration

Set the properties in [rapido.properties][rp] to connect to Accumulo.

### Integration Testing

Run a specific IT:
```bash
mvn clean test -Dtest=app.it.QueryControllerIT
```


[Accumulo]: https://accumulo.apache.org
[Uno]: https://github.com/apache/fluo-uno
[rp]: https://github.com/milleruntime/rapido/blob/master/src/main/resources/config/rapido.properties

