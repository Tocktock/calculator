# Build stage
FROM gradle:8.5.0-jdk17 AS builder
WORKDIR /build/

# Utilize build cache to avoid re-downloading dependencies
COPY build.gradle.kts settings.gradle.kts ./
RUN gradle build --dry-run --no-daemon

# Copy source code and build the application
COPY src src
RUN gradle build -x test --no-daemon

RUN ls -al

# Package stage
FROM amazoncorretto:17
WORKDIR /app

COPY --from=builder /build/build/libs/app.jar .

EXPOSE 8080

# Copy the built jar file from the build stage

ENTRYPOINT ["java", "-jar", "app.jar"]
