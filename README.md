# Book Management API

A sample Spring Boot application demonstrating best practices for building production-grade REST APIs. This project uses Spring Boot, Spring Data JPA, and PostgreSQL, and it shows how to structure your code using DTOs, service layers, global exception handling, logging with log rotation, and pagination.

## Features

- **CRUD Operations for Books:**  
  Create, update, delete, and retrieve books using RESTful endpoints.

- **DTO-Based Data Binding:**  
  Separate DTOs for request and response to decouple API contracts from internal data models.

- **Global Exception Handling:**  
  Centralized exception handling with a consistent error response format using an `ApiResponse` wrapper.

- **Pagination:**  
  Built-in support for pagination using Spring Data's `Pageable` and custom pagination response DTOs.

- **Robust Logging:**  
  Configured with Logback for console and file logging, with automatic daily log rotation and size-based rollover.

- **Optimistic Locking:**  
  (Optional) Optimistic locking to handle concurrent updates, ensuring data consistency.

## Project Structure

```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/example/bookapi/
│   │       ├── controller/
│   │       │   └── BookController.java
│   │       ├── dto/
│   │       │   ├── BookRequestDTO.java         // For create requests
│   │       │   ├── BookUpdateDTO.java          // For update requests (supports partial updates)
│   │       │   ├── BookResponseDTO.java        // For API responses
│   │       │   └── PageResponse.java           // Wraps paginated results with metadata
│   │       ├── exception/
│   │       │   ├── GlobalExceptionHandler.java // Centralized exception handling using @RestControllerAdvice
│   │       │   └── BookNotFoundException.java  // Custom exception example
│   │       ├── model/
│   │       │   └── Book.java                    // JPA entity for Book
│   │       ├── repository/
│   │       │   └── BookRepository.java
│   │       ├── service/
│   │       │   └── BookService.java             // Business logic & transaction management
│   │       └── BookApiApplication.java          // Main Spring Boot application class
│   └── resources/
│       ├── application.properties             // Base configuration; activates appropriate profiles
│       ├── application-dev.properties         // Development-specific configuration
│       └── logback-spring.xml                 // Logback configuration for logging and rotation
└── test/
    └── java/ ...                              // Unit and integration tests
```

## Prerequisites

- **Java 17 or later**
- **Gradle or Maven:**  
  The sample uses Gradle (see `build.gradle` for dependency definitions).
- **PostgreSQL:**  
  Ensure PostgreSQL is installed and running, and update your `application.properties` with the correct database URL, username, and password.

## Setup & Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/book-api.git
   cd book-api
   ```

2. **Configure the Database:**

   Update `src/main/resources/application-dev.properties` (or your active profile) with your PostgreSQL connection details:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/mydb
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=create-drop  # For testing; change appropriately for production
   ```

3. **Build the Project:**

   Using Gradle:

   ```bash
   ./gradlew build
   ```

   Or with Maven:

   ```bash
   mvn clean install
   ```

4. **Run the Application:**

   ```bash
   ./gradlew bootRun
   ```

   The application will start on the default port (8080).

## API Endpoints

- **GET /books:**  
  Retrieves a paginated list of books. Accepts pagination query parameters (`page`, `size`, `sort`) and returns an `ApiResponse` wrapping a `PageResponse<BookResponseDTO>`.

- **POST /books:**  
  Creates a new book. Uses `BookRequestDTO` for input and returns a standardized response.

- **PUT /books/{id}:**  
  Updates an existing book. Uses `BookUpdateDTO` to allow partial updates.

- **DELETE /books/{id}:**  
  Soft-deletes a book (if implemented with a boolean flag).

## Exception Handling & API Responses

The project uses a global exception handler (`GlobalExceptionHandler`) with `@RestControllerAdvice` to ensure a consistent API error response structure using the `ApiResponse` class. Some handled cases include:

- **Validation Errors:**  
  Returns detailed error messages for request body validation failures.

- **Constraint Violations:**  
  Handles method parameter and path variable validations.

- **Missing Request Parameters, Unsupported Methods/Media Types:**  
  Provides appropriate HTTP statuses and error messages.

- **General Exceptions:**  
  A catch-all for unexpected errors, returning HTTP 500.

## Logging & Log Rotation

Logging is configured via `logback-spring.xml`:

- **Console Logging:**  
  All logs are printed to the console during development.

- **File Logging with Rolling Policy:**  
  Logs are written to a file (`logs/app.log`), rotated daily or when a file size threshold (e.g., 10MB) is exceeded. Older logs are archived and compressed, with up to 30 days of history retained.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes. For major changes, open an issue first to discuss what you would like to change.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---