# Use the official OpenJDK 17 image as the base image
FROM eclipse-temurin:17

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged WAR file into the container at /app
COPY target/task-management-api-1.0.0.war /app

RUN mkdir /app/logs

# Expose the port that your application runs on
EXPOSE 8688

# Specify the command to run your application
CMD ["java", "-jar", "-Dspring.profiles.active=prd", "task-management-api-1.0.0.war"]
