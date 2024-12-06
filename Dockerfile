FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar /app/ms-price-lookup.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/ms-price-lookup.jar"]