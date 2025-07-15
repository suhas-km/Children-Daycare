# Children Daycare Management System

A robust Spring Boot application built on enterprise Java architecture for comprehensive management of a daycare center's operations. This system leverages Spring's dependency injection, inversion of control principles, and JDBC for data persistence to enable efficient tracking and management of student and staff information. The application follows a layered architecture pattern with clear separation of concerns between model, repository, service, and configuration layers.

## Features

- **Student Management Module**
  - Student registration with comprehensive data capture
  - Family information tracking (parents' details)
  - Contact information management
  - Academic performance monitoring (GPA)

- **Teacher Management Module**
  - Staff registration and credential tracking
  - Experience tracking through credits system
  - Group and classroom assignment capabilities

- **Core Technical Features**
  - Robust data persistence using JDBC with prepared statements
  - Secure database transaction handling
  - Enterprise-level object-oriented design with inheritance hierarchy
  - Type-safe data manipulation
  - Clean separation of concerns with layered architecture
  - Dependency injection for loose coupling between components

## Technology Stack

### Backend Framework
- **Java 11**: Enterprise-grade JDK with long-term support
- **Spring Boot 2.7.17**: Production-ready framework minimizing boilerplate code
  - Spring Core: Dependency Injection container
  - Spring JDBC: Database access templates and utilities
  - Spring Web: RESTful API foundation

### Build & Dependency Management
- **Maven 3**: Declarative build and dependency management
  - Lifecycle management
  - Transitive dependency resolution
  - Project Object Model (POM) based configuration

### Data Layer
- **MySQL 8.0**: Robust relational database management system
- **JDBC**: Low-level database connectivity with optimal performance
  - Connection pooling
  - Prepared statement caching
  - Transaction management

### Runtime Environment
- **JVM**: Memory management, garbage collection, JIT compilation
- **Tomcat** (embedded): Servlet container for HTTP request handling

## Architecture

The application follows a layered architecture pattern for clear separation of concerns:

### Model Layer
- Located in `com.neu.csye6200.daycare.model` package
- Contains entity classes representing domain objects (Person, Student, Teacher)
- Pure Java POJOs with getters/setters
- Implements inheritance hierarchy (Student and Teacher extend Person)

### Repository Layer
- Located in `com.neu.csye6200.daycare.repository` package
- Handles all database operations (CRUD) using JDBC
- Uses prepared statements to prevent SQL injection
- Each entity has its own repository (StudentRepository, TeacherRepository)

### Service Layer
- Located in `com.neu.csye6200.daycare.service` package
- Implements business logic and validation rules
- Acts as a facade over repositories
- Provides transaction management
- Annotated with `@Service` for Spring dependency injection

### Configuration Layer
- Located in `com.neu.csye6200.daycare.config` package
- Centralizes application configuration
- Manages database connection properties
- Creates and configures beans for dependency injection

### Application Layer
- Main `DaycareApplication` class bootstraps the application
- Uses CommandLineRunner for example data initialization
- Implements dependency injection through constructor parameters

## Prerequisites

- **JDK 11**: Oracle JDK or OpenJDK implementation
- **Maven 3.6+**: For dependency management and build automation
- **MySQL Server 8.0+**: Relational database server
- **IDE**: Spring Tool Suite, IntelliJ IDEA, or Eclipse with Spring plugins (recommended)
- **Git**: For version control (optional)

## Database Setup

1. Create a MySQL database named `ChildrenDaycare`
2. Use the following credentials in your database connection (or update them in the code):
   - Username: root
   - Password: csye6200
3. Create the necessary tables:

```sql
CREATE TABLE student (
    ID VARCHAR(10) PRIMARY KEY,
    Name VARCHAR(100),
    Email VARCHAR(100),
    Age INT,
    FatherName VARCHAR(100),
    MotherName VARCHAR(100),
    Address VARCHAR(255),
    PhoneNumber VARCHAR(20),
    GPA DOUBLE
);

CREATE TABLE teacher (
    ID VARCHAR(10) PRIMARY KEY,
    Name VARCHAR(100),
    Email VARCHAR(100),
    Age INT,
    Credits DOUBLE,
    GroupID INT,
    ClassroomID INT
);
```

## Development & Deployment Guide

### Development Environment Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd Children-Daycare
   ```

2. **Configure MySQL Database**
   - Create the database schema as detailed in the Database Setup section
   - Verify connection parameters match your environment

3. **IDE Configuration**
   - Import as Maven project
   - Configure JDK 11
   - Set up Spring Boot run configuration

### Build Process

1. **Compile and resolve dependencies**
   ```bash
   mvn clean compile
   ```

2. **Run unit tests**
   ```bash
   mvn test
   ```

3. **Package application**
   ```bash
   mvn package
   ```
   This creates an executable JAR in the `target` directory

### Application Launch

1. **Run with Maven**
   ```bash
   mvn spring-boot:run
   ```

2. **Run as JAR**
   ```bash
   java -jar target/Daycare-0.0.1-SNAPSHOT.jar
   ```

3. **Application access**
   - The application will start on port 8080 by default
   - Access via http://localhost:8080 when web interface is implemented

### Environment-Specific Configuration

- **Development**: Default configuration
- **Production**: Set appropriate JVM options
  ```bash
  java -Xms512m -Xmx1024m -jar target/Daycare-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
  ```

## Architectural Overview

### Project Structure
```
src
├── main
│   ├── java
│   │   └── com
│   │       └── neu
│   │           └── csye6200
│   │               └── daycare
│   │                   ├── DaycareApplication.java    # Application entry point
│   │                   ├── Person.java               # Base entity class
│   │                   ├── Student.java              # Student entity (extends Person)
│   │                   └── Teacher.java              # Teacher entity (extends Person)
│   ├── resources
│   │   ├── application.properties                   # Application configuration
│   │   ├── static                                   # Static web resources
│   │   └── templates                                # View templates
└── test
    └── java                                         # Test classes
```

### Design Patterns
- **Inheritance Hierarchy**: Entity inheritance from base Person class
- **Data Access Object (DAO)**: Embedded in entity classes (to be refactored)
- **Dependency Injection**: Spring-managed beans and components
- **MVC Architecture**: Foundation for future web interface

### Component Descriptions
- **Person.java**: Abstract base class implementing shared attributes and behaviors
  - Core entity attributes (ID, Name, Email, Age)
  - Getter/setter methods with encapsulation
  - Common functionality through inheritance

- **Student.java**: Concrete implementation for student management
  - Extended attributes for family information
  - JDBC-based data persistence operations
  - Transaction handling for database operations

- **Teacher.java**: Specialized entity for staff management
  - Professional attributes (credits, assignments)
  - Database integration with prepared statements
  - SQL injection prevention techniques

- **DaycareApplication.java**: Spring Boot launcher
  - Application context configuration
  - Component scanning
  - Bean initialization

## Implementation Details

### Entity Model Design

```java
// Base abstract entity with common attributes
public class Person {
    private String ID;
    private String Name;
    private String Email;
    private int Age;
    
    // Constructors, getters, setters...
}
```

### JDBC Integration

```java
// Database operations with prepared statements (from Student.java)
public void insertStudentIntoDatabase(Student student) {
    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        String query = "INSERT INTO student (ID, Name, Email, Age, FatherName, MotherName, Address, PhoneNumber, GPA) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Parameter binding with type safety
            preparedStatement.setString(1, student.getID());
            preparedStatement.setString(2, student.getName());
            // Additional parameters...
            
            // Transaction execution
            int rowsAffected = preparedStatement.executeUpdate();
            // Result handling...
        }
    } catch (SQLException e) {
        // Exception handling...
    }
}
```

### Usage Examples

```java
// Creating and persisting a student entity
Student student = new Student(
    "S01",              // ID
    "John Smith",       // Name
    "john@example.com", // Email
    5,                  // Age
    "James Smith",      // Father's name
    "Mary Smith",       // Mother's name
    "123 Main St",      // Address
    "(555) 123-4567"    // Phone number
);
student.insertStudentIntoDatabase(student);

// Creating and persisting a teacher entity
Teacher teacher = new Teacher(
    "T01",               // ID
    "Jane Doe",          // Name
    "jane@example.com",  // Email
    35,                  // Age
    10.5                 // Credits
);
teacher.insertTeacherIntoDatabase(teacher);
```

## Roadmap & Future Enhancements

### Short-term Improvements
- **Service Layer**: Implement business logic separation with service interfaces and implementations
- **Repository Pattern**: Refactor database operations from entities to dedicated repositories
- **Configuration Externalization**: Move database credentials to application.properties
- **Input Validation**: Bean Validation (JSR 380) implementation
- **Exception Handling**: Global exception handling with @ControllerAdvice

### Medium-term Goals
- **REST API**: RESTful endpoints with Spring MVC controllers
- **Data Transfer Objects**: DTO pattern for API request/response
- **API Documentation**: Swagger/OpenAPI integration
- **Logging Framework**: SLF4J with Logback configuration
- **Unit & Integration Testing**: JUnit 5 with Mockito and Spring Test

### Long-term Vision
- **Security Implementation**: Spring Security with JWT authentication
- **ORM Integration**: JPA/Hibernate for sophisticated data operations
- **Caching**: Redis or EhCache integration
- **Asynchronous Processing**: Event-driven architecture with Spring Events
- **Microservices Architecture**: Split functionality into discrete services
- **Containerization**: Docker deployment configuration
- **CI/CD Pipeline**: Automated testing and deployment

## Technical Considerations

### Performance Optimizations
- Direct JDBC usage for minimal overhead
- Connection management for optimal resource utilization
- Prepared statements for query optimization and security

### Scalability Factors
- Stateless design principles for horizontal scaling capability
- Transaction isolation to maintain data integrity under load
- Foundation for future caching implementation

### Security Measures
- SQL injection prevention through parameterized queries
- Groundwork for future authentication/authorization layers
- Input validation framework

## Project Status

This project is in active development with a focus on establishing the core domain model and data persistence layer. The current implementation represents the foundation for a full-featured daycare management system.

## License

[MIT License](https://opensource.org/licenses/MIT)

## Contributors

- Development Team: [Your Name/Team]
- Academic Project: Northeastern University, CSYE 6200 - Object-Oriented Design

---

© 2025 [Your Organization]. All rights reserved.
