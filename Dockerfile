# Utilisez l'image OpenJDK 11 avec le client MySQL comme base
FROM openjdk:11-jdk
# Installez le client MySQL
RUN apt-get update && apt-get install -y default-mysql-client && rm -rf /var/lib/apt/lists/*
# Définissez le répertoire de travail
WORKDIR /app
# Copiez le fichier JAR de votre application Spring Boot dans le répertoire de travail
COPY target/LocTest-0.0.1-SNAPSHOT.jar LocTest.jar
# Exposez le port utilisé par votre application Spring Boot
EXPOSE 8080
# Commande pour exécuter l'application Spring Boot lorsque le conteneur démarre
CMD ["java", "-jar", "LocTest.jar"]
