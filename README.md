
# Task Tracker API

The **Task Tracker API** is a backend service built using Java Spring Boot. This API serves as the core of the Task Tracker application, providing all necessary endpoints for managing tasks, users, and associated operations. It handles the business logic, data persistence, and communication with other services, such as message brokers like Kafka.

## Features

- **Task Management:** Create, update, delete, and retrieve tasks within the Task Tracker system.
- **User Management:** Manage user profiles and roles, allowing for the assignment and tracking of tasks by users.
- **Integration with Kafka:** Publish and consume messages related to task creation, updates, and deletion, enabling real-time notifications and event-driven architecture.
- **Data Persistence:** Utilize relational databases (e.g., SQLite for development, Amazon Aurora for production) for storing and querying task and user data.
- **RESTful API:** Expose a RESTful interface for clients to interact with the Task Tracker application, ensuring a standardized approach to API design and communication.
- **Security:** Implement authentication and authorization mechanisms to protect API endpoints, ensuring that only authorized users can perform certain operations.

## Getting Started

### Prerequisites

- **Java 11** or later
- **Maven** for building the project
- **Spring Boot** for application framework
- **MySql** for local development
- **Amazon Aurora DB** for production (optional)
- **Kafka** for event messaging (optional but recommended)

### Installation MySql Docker

   ```bash
   # Pull the latest MySQL image
   docker pull mysql:latest
   
    # Create network for task-tracker
   docker network create --driver bridge task-tracker-network
   
   # Run a mysql container based on this image
   docker compose -f docker-compose-mysql.yml backup
   
   # To check MySQL container is running
   docker ps
   
   # Create the database and tables
   docker exec -i mysql mysql -u root -p<enter-password> < <path-to-file>/create_database.sql   
   docker exec -i mysql mysql -u root -p<enter-password> < <path-to-file>/insert_user_group_category_records.sql   
   
    # Access the MySQL Monitor
   docker exec -it mysql-container mysql -u root -p
   
   #example MySQL command:
   #mysql> USE <database-name>;
   #mysql> SHOW DATABASES;
   #mysql> SHOW TABLES;
   #mysql> SHOW PROCESSLIST;
   #mysql> DESCRIBE <table>;
   #mysql> SELECT VERSION();
   
   # Backup a Database
   docker exec mysql mysql -u root -p<password> my_database > /path/to/backup/my_database_backup.sql
   
   # Restore a Database from a Backup
   docker exec -i mysql mysql -u root -p<password> my_database < /path/to/backup/my_database_backup.sql

   # Exiting the MySQL CLI
   #mysql> EXIT;
   
   
   
   
   ```
2. 
### Installation

1. **Clone the Repository:**

   ```bash
   git https://github.com/NUS-ISS-SE-Group06/task-management.git
   cd task-management
   ```

2. **Build the Project:**

   Use Maven to build the project:

   ```bash
   mvn clean install
   ```

3. **Run the Application:**

   Start the Spring Boot application:

   ```bash
   mvn spring-boot:run
   ```

   Alternatively, you can run the packaged JAR file:

   ```bash
   java -jar target/task-tracker-api-0.0.1-SNAPSHOT.jar
   ```
3. **🐳 Alternatively, build the Docker image:**

   ```bash
   docker build -t task-management .
   ```

3. **🐳 Run the Docker image:**
   ```bash
   docker compose up
   ```
   
### Configuration 

The application can be configured via the `application.yml` or `application.properties` file located in the `src/main/resources` directory. Key configurations include:

- **Database Configuration:**
  Configure the database connection for development (SQLite) and production (Amazon Aurora).

- **Kafka Configuration:**
  Set up Kafka integration by configuring the bootstrap servers and topic names.

