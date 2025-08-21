# Use Java 17 (Render supports it)
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies (better caching)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Package the application
RUN ./mvnw clean package -DskipTests

# Run the jar file
CMD ["java", "-jar", "target/talenthunt-0.0.1-SNAPSHOT.jar"]
