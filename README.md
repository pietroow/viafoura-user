# Viafoura - User Management

## Goal
Validate the candidate coding skills for an intermediate position.

## Key concepts to validate
- API design & documentation
- Project structure
- Java
- Dependency Injection Framework
- MySQL
- Tests
- Dockerization

## Challenge
We need to create a new microservice to handle the users of the system. It’s important to mention we would like to use OAS3 specification
to document the endpoints.
How would you structure the project considering the Company is following Hexagonal architecture?
Would you recommend any endpoint/s before starting with the User’s management? If yes, please implement it. If not, let’s proceed!
Requirements

1. Define & implement an endpoint to list all users. We can define users have:
   - Email (unique for the system)
   - First name
   - Last name
   - Friends including first name and last name.
2. Design the schema considering performance. Consider PKs/FKs etc.
3. Define & implement create user endpoint
4. Define & implement update user endpoint
5. Define & implement retrieve user by id endpoint
6. Define & implement delete user endpoint.
7. Include user’s avatar calling to [Reqres](https://reqres.in/]) - A hosted REST-API ready to respond to your AJAX requests in order to retrieve it and
   include them to your response. What strategy can you apply to handle different kind of issues? Can you implement any of them?
   
8. Write Unit tests for creating users minimally.
9. Dockerize the solution
10. Would you include anything else considering you’re part of a team and other devs will be working with you?

___
## Setup and Installation

### Prerequisites
- **Java Version**: Ensure you have Java 17 installed.

### Running with an IDE
1. Open your favorite IDE.
2. run `./gradlew build`
2. Run the [UserApplication.java](src%2Fmain%2Fjava%2Fcom%2Fviafoura%2Fuser%2FUserApplication.java) 

### Running with Docker
1. Build the Docker image:
    ```sh
    docker build -t viafoura-user-app .
    ```
2. Run the Docker container:
    ```sh
    docker run -p 8080:8080 viafoura-user-app
    ```
3. The application will be available on port 8080.

## Usage
- It includes extensive unit tests to validate Jakarta Validators (requests).
- Integration tests using RestAssured are implemented to validate endpoints and various use cases.

## Configuration
- The `application.properties` file contains minimal configuration to support all necessary features.
- The application boasts a high test coverage.

## Application Structure

### Adapter Layer: 
Handles interactions between the application and the outside world. This is divided into outbound (for outgoing interactions like API calls and database operations) and potentially inbound if you have any other external inputs coming into the application that aren't via HTTP.
### Domain Layer: 
Contains the core business logic and the primary entities/models of the application.
### Port Layer: 
Interfaces that define the primary inputs and outputs of the application, separating the core logic from the adapters.

### Exception Handling
- The application includes a custom exception handling mechanism using [CustomExceptionHandler.java](src%2Fmain%2Fjava%2Fcom%2Fviafoura%2Fuser%2Fexception%2FCustomExceptionHandler.java).

### Testing
- **Unit Tests**: Validate the functionality of individual components, focusing on Jakarta Validators. [BaseControllerTest.java](src%2Ftest%2Fjava%2Fcom%2Fviafoura%2Fuser%2Fcontroller%2FBaseControllerTest.java)
- **Integration Tests**: Use RestAssured to test endpoints and validate various use cases, ensuring the overall system works as expected. [BaseIntegrationTest.java](src%2Ftest%2Fjava%2Fcom%2Fviafoura%2Fuser%2Fintegration%2FBaseIntegrationTest.java)

This structure ensures a clean separation of concerns, maintainability, and the ability to easily adapt to future changes or enhancements.
