ARG VERSION="0.0.1-SNAPSHOT"

FROM eclipse-temurin:21.0.11_10-jre-jammy

WORKDIR /app
COPY target/site-*.jar /app/site.jar
RUN mkdir -p /app/mnt

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "site.jar"]