# Price Lookup Service

## Application Overview 😎

The Price Lookup Service is a microservice designed to retrieve and calculate applicable product prices based on the date, brand, and product. This application follows a hexagonal architecture, ensuring a clear separation between business logic and external interfaces, which enhances maintainability and scalability.

## Main Features 📄

Price lookup by:
- Product ID.
- Brand ID.
- Application date.

Returns the applicable price with the highest priority based on business rules. Uses an embedded H2 database for temporary data storage
Implements hexagonal architecture to decouple business logic from external dependencies
Includes unit tests to ensure code quality.

## Architecture 🗂️
The project adheres to the Hexagonal Architecture (Ports and Adapters) principles. The main components are:

1. Domain:
   * Contains core business logic and entities, such as Price.
   * Interfaces for interacting with ports.
2. Application
   * Implements specific use cases.
   * Coordinates interactions between the domain and adapters.
3. Infrastructure
   * Adapters: Database connections and other external dependencies
4. Rest
   * REST controllers to expose APIs

Configuración de frameworks y herramientas externas como Spring Boot y Docker.

## H2 Database Configuration 🛠
The application uses an embedded H2 database for development

Default configuration:
```
spring:
    datasource:
        initialization-mode: always
        url: jdbc:h2:mem:inditex
        driver-class-name: org.h2.Driver
        username: app
        password:
    h2:
        console:
            enabled: true
            path: /h2-console
```
### Relevant Files 
#### [schema.sql](./src/main/resources/schema.sql)
Defines the database schema (structure).

Contains SQL commands to create tables, constraints, and relationships.
Executed by Spring Boot before the application starts if database initialization is enabled
```
spring:
    datasource:
        initialization-mode: always
```

#### [data.sql](./src/main/resources/data.sql)
Populates the database with initial or seed data.

Contains SQL commands to insert data into the tables created by schema.sql.
Executed after schema.sql to fill the tables with default or test data.

### Accessing the H2 Console:
* Start the application.
* Navigate to http://localhost:8080/h2-console.
* Use the credentials mentioned above to connect.

## Deployment 🚀
To compile the project, simply execute the following command
```
mvn clean package
```
To deploy this API locally we must have java version 21 installed. It is recommended to use the IntelliJ IDE.
The steps to run locally are:

1. Clone the repository in a local folder.

2. Configure the DB from the application.yml file if necessary.

3. Start the application from the App.java class (Right click on the class -> Run 'App.main()').
 
4. The application by default will start on host 127.0.0.1 on port 8080.

## Deployment with Docker 🐋

### Prerequisites
- Docker and Docker Compose must be installed on your machine

### Deployment Steps
1. Build the Docker container:
```
docker build -t ms-price-lookup .
```
2. Start the service using Docker Compose:
```
docker-compose up
```
3. Access the service at http://localhost:8080.


## Testing ✅
The application includes unit tests to validate the main use cases, following a naming convention that clearly describes the scenario under test.

Run Tests:
```
mvn test
```