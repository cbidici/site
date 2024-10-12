ARG VERSION="0.0.1-SNAPSHOT"

# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file from the target directory to the container
COPY target/site-*-app.jar /app/site.jar

# Create a directory for the SQLite database inside the container
RUN mkdir -p /app/db

# Expose the port the app runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "site.jar"]