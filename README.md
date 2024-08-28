
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
- **SQLite** for local development
- **Amazon Aurora DB** for production (optional)
- **Kafka** for event messaging (optional but recommended)

### Installation

1. **Clone the Repository:**

   \`\`\`bash
   git clone https://github.com/your-username/task-tracker-api.git
   cd task-tracker-api
   \`\`\`

2. **Build the Project:**

   Use Maven to build the project:

   \`\`\`bash
   mvn clean install
   \`\`\`

3. **Run the Application:**

   Start the Spring Boot application:

   \`\`\`bash
   mvn spring-boot:run
   \`\`\`

   Alternatively, you can run the packaged JAR file:

   \`\`\`bash
   java -jar target/task-tracker-api-0.0.1-SNAPSHOT.jar
   \`\`\`

### Configuration 

The application can be configured via the `application.yml` or `application.properties` file located in the `src/main/resources` directory. Key configurations include:

- **Database Configuration:**
  Configure the database connection for development (SQLite) and production (Amazon Aurora).

- **Kafka Configuration:**
  Set up Kafka integration by configuring the bootstrap servers and topic names.

