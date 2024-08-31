
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
   
   # Run a container based on this image
   docker run --name mysql-container \
    -e MYSQL_ROOT_PASSWORD=<set-password-here> \
    -p 3306:3306 \
    -v /Users/<user-profile>/nus/db:/varlib/mysql \ 
    -d mysql:latest 
    
   # To check MySQL container is running
   docker ps
   
   # Copy SQL files to the Docker container
   docker cp /<path-to-source-file>/resources/db/create_database.sql mysql-container:/create_database.sql
   docker cp /<path-to-source-file>/resources/db/insert_user_group_category_records.sql mysql-container:/insert_user_group_category_records.sql
  
   # Create the database and tables
   docker exec -i mysql-container mysql -u root -proot < ./create_database.sql   
   docker exec -i mysql-container mysql -u root -proot < ./creatinsert_user_group_category_recordse_database.sql   
   
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
   docker exec mysql-container mysql -u root -p<password> my_database > /path/to/backup/my_database_backup.sql
   
   # Restore a Database from a Backup
   docker exec -i mysql-container mysql -u root -p<password> my_database < /path/to/backup/my_database_backup.sql

   # Exiting the MySQL CLI
   #mysql> EXIT;
   
   
   
   
   ```
2. 
### Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/your-username/task-tracker-api.git
   cd task-tracker-api
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

### Configuration 

The application can be configured via the `application.yml` or `application.properties` file located in the `src/main/resources` directory. Key configurations include:

- **Database Configuration:**
  Configure the database connection for development (SQLite) and production (Amazon Aurora).

- **Kafka Configuration:**
  Set up Kafka integration by configuring the bootstrap servers and topic names.

