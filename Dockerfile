# Stage 1: Use Maven with JDK 21 to build the project
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copy pom.xml first (to cache dependencies)
COPY pom.xml .
RUN mvn dependency:go-offline  # Pre-download dependencies

# Copy the remaining source files
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Stage 2: Use a lightweight JDK 21 runtime
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/admin-system-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
