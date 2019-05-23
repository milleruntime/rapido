# Rapido

A fast lightweight query app for Accumulo.

Rapido uses Spring Boot to create a self contained Jar that is deployed with Embedded Tomcat.

### Requirements:
Java 11

## To create the Tomcat Embedded JAR

mvn clean package 

## To Deploy

java -jar target/rapido-*.jar

## Or Both in one Command!

mvn clean package && java -jar target/rapido-*.jar
