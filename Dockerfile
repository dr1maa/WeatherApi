FROM openjdk:21-jdk-slim
COPY target/weather-api-0.0.1-SNAPSHOT.jar weather-api.jar
ENTRYPOINT ["java", "-jar", "weather-api.jar"]