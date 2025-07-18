# ──────── Stage 1: Build with Maven ────────
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /build

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# ──────── Stage 2: Run with AI agent ────────
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy built JAR
COPY --from=build /build/target/instagram-app.jar app.jar

# Copy AI agent and config (added by pipeline before build)
COPY applicationinsights-agent-3.7.1.jar applicationinsights-agent.jar
COPY applicationinsights.json applicationinsights.json

ENTRYPOINT ["java", "-javaagent:/app/applicationinsights-agent.jar", "-jar", "app.jar"]