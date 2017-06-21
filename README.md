# LO54-JEE-MiniProjet
LO54 JEE MiniProjet

A really simple project used to experiment with some jee technology. 

## Compile and run 
### Broker
mvn install
java -jar broker_jms-1.0-SNAPSHOT-jar-with-dependencies.jar
### App
mvn war:war
deploy and run in tomcat 8 app-1.0-SNAPSHOT.war with the javadb set to the base available in conception/final.sql
