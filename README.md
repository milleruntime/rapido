## To create the WAR

mvn compile war:war

## To Deploy the WAR

cp ./target/aquery-*.war $JETTY_HOME/webapps

## To deploy exploded WAR

cp -r aq/ $JETTY_HOME/webapps

Coming soon: Running Jetty in maven with the jetty-maven-plugin 
