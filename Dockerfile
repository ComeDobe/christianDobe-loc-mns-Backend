## Start with a base image containing Java runtime (OpenJDK 11)
#FROM openjdk:11-jdk-slim as builder
#
## The application's .jar file will reside inside /app
#WORKDIR /app
#
## Package the application
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#COPY src ./src
#
## Package the application
#RUN ./mvnw package -DskipTests
#
## Runtime stage
#FROM openjdk:11-jre-slim
#
#WORKDIR /app
#
## Copy the jar file built in the builder stage
#COPY --from=builder /app/target/LocTest-0.0.1-SNAPSHOT.war LocTest.war
#
## Expose the port
#EXPOSE 8080
#
## Run the application
#ENTRYPOINT ["java", "-jar", "LocTest.war"]

#
#
## Use Tomcat version 8.5 as the base
#FROM tomcat:8.5
## Copy the WAR file to the Tomcat webapps directory
#COPY target/LocTest-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
## Use the Catalina script to run Tomcat
#CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]






# Utilisez l'image OpenJDK 11 avec le client MySQL comme base
FROM openjdk:11-jdk
# Installez le client MySQL
# Définissez le répertoire de travail
WORKDIR /app
# Copiez le fichier JAR de votre application Spring Boot dans le répertoire de travail
COPY target/LocTest-0.0.1-SNAPSHOT.jar LocTest.jar
# Exposez le port utilisé par votre application Spring Boot
EXPOSE 8080
# Commande pour exécuter l'application Spring Boot lorsque le conteneur démarre
CMD ["java", "-jar", "LocTest.jar"]