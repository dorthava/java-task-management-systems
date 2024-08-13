FROM maven:3.9.8-amazoncorretto-17-al2023 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder app/target/tasks-0.0.1-SNAPSHOT.jar tasks-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/tasks-0.0.1-SNAPSHOT.jar"]