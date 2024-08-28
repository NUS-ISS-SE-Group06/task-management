# Use the official OpenJDK 17 image as the base image
FROM eclipse-temurin:17

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged WAR file into the container at /app
COPY target/task-tracker-api-1.0.0.war /app

RUN mkdir /app/db

RUN mkdir /app/logs

COPY target/tasktracker.db /app/db

# Expose the port that your application runs on
EXPOSE 8688

# Specify the command to run your application
CMD ["java", "-jar", "-Dspring.profiles.active=prd", "task-tracker-api-1.0.0.war"]
#CMD ["java", "-jar", "task-tracker-api-1.0.0.war"]
