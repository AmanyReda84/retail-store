# Dockerfile
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

COPY pom.xml .

COPY compose.yaml .

RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Run the tests
RUN mvn test

# Package the application
RUN mvn package -DskipTests

# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /app/app.jar

# Copy  compose file from the build stage
COPY --from=build /app/compose.yaml /app/compose.yaml


# Set environment variables
#ENV SPRING_DOCKER_COMPOSE_FILE=/app/compose.yaml

#"-Dspring.docker.compose.file=${SPRING_DOCKER_COMPOSE_FILE}",
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
