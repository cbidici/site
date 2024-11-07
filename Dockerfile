ARG VERSION="0.0.1-SNAPSHOT"

FROM openjdk:21-jdk

WORKDIR /app
COPY target/site-*.jar /app/site.jar
RUN mkdir -p /app/mnt

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "site.jar"]