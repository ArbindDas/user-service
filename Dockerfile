FROM openjdk:25-jdk-slim

WORKDIR /app

# Copy the specific JAR file (not wildcard)
COPY target/user-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]