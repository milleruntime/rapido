# Rapido
### A fast lightweight query app for Accumulo

## To create the WAR

mvn compile war:war

## To Deploy the WAR

cp ./target/rapido-*.war $JETTY_HOME/webapps

## To deploy exploded WAR

cp -r ./target/rapido $JETTY_HOME/webapps

