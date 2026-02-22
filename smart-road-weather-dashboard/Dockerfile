# ============================
# Stage 1: Build the application
# ============================
FROM eclipse-temurin:17-jdk AS build

# Set working directory
WORKDIR /app

# Copy Maven descriptor first (for dependency caching)
COPY pom.xml .

# Copy source code
COPY src ./src

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the application (skip tests for faster build, remove -DskipTests if you want tests to run)
RUN mvn clean package -DskipTests

# ============================
# Stage 2: Run the application
# ============================
FROM eclipse-temurin:17-jre

# Set working directory
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
