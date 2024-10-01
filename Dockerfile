FROM gradle:7.4.2-jdk17-alpine AS builder
WORKDIR /app
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle
COPY src /app/src
RUN chmod +x ./gradlew && ./gradlew build

FROM eclipse-temurin:17-jdk-alpine

COPY --from=builder /app/build/libs/viafoura.jar /app/app.jar
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]