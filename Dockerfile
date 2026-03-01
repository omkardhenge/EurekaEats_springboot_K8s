# Use official Java 21 runtime (lightweight JRE)
FROM eclipse-temurin:21-jre

# Set working directory in container
WORKDIR /app

# Copy your built JAR into the container
COPY target/order-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app listens on
EXPOSE 9094

# Use exec form ENTRYPOINT for proper signal handling
ENTRYPOINT ["java", "-jar", "/app/app.jar"]