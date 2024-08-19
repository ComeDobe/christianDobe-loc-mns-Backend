FROM openjdk:11-
RUN apt-get update && apt-get install -y default-mysql-client && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY target/LocTest-0.0.1-SNAPSHOT.jar LocTest.jar
EXPOSE 8080
CMD ["java", "-jar", "LocTest.jar"]
