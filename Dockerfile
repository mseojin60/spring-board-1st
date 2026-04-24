# 1. 빌드 단계
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test

# 2. 실행 단계
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
