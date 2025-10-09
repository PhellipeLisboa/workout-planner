FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -B clean package -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/target/workoutplanner-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]