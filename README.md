# Blog Platform

## Description
This Blog Platform is a robust application built on Spring Boot, designed to offer comprehensive blogging functionalities. It leverages Java 21 and Spring Boot 3 to demonstrate modern Java development practices along with a RESTful API design, providing a reliable and scalable solution for content management.

## Features
- **CRUD Operations**: Users can create, read, update, and delete blog posts using a clean and intuitive interface.
- **Data Persistence**: Utilizes MySQL for reliable data storage, ensuring data integrity and support for transactions.
- **RESTful API**: Includes well-defined endpoints for managing blog posts and user interactions.
- **User Authentication**: Implements basic authentication to secure user accounts and sessions.
- **Responsive Design**: Supports a responsive frontend design, adaptable to various devices and screens.

## Technologies
- **Spring Boot 3**: Uses the latest Spring Boot framework for building standalone and production-grade applications.
- **Java 21**: Leverages the latest Java features and security enhancements.
- **MySQL**: Employs MySQL as a robust database management system for storing blog posts and user data.
- **Maven**: Utilizes Maven for efficient project dependency management and build processes.

## API Endpoints

| Method | Endpoint           | Description                        |
|--------|--------------------|------------------------------------|
| GET    | `/api/posts`       | Retrieves all blog posts.          |
| GET    | `/api/posts/{id}`  | Retrieves a blog post by ID.       |
| POST   | `/api/posts`       | Creates a new blog post.           |
| PUT    | `/api/posts/{id}`  | Updates an existing blog post.     |
| DELETE | `/api/posts/{id}`  | Deletes a blog post.               |

## Project Structure

### Main Files
- **BlogApplication.java**: Entry point of the Spring Boot application, annotated with `@SpringBootApplication`.
- **LoginService.java**: Manages user authentication and session, implementing the `UserDetailsService` for Spring Security integration.
- **PostService.java**: Encapsulates business logic for managing blog posts, supporting CRUD operations.
- **UserService.java**: Handles user-related business logic, including user validation and data integrity.

### Repository Files
- **PostRepository.java**: JPA repository for blog posts, includes custom query methods for enhanced search capabilities.
- **UserRepository.java**: Extends JpaRepository for user management, facilitating user search and validation processes.

### Model Files
- **Post.java**: Entity model representing a blog post, including attributes like title, content, and timestamps.
- **User.java**: Entity model for user management, ensuring secure password handling and role-based access control.

### Controller Files
- **PostController.java**: Manages HTTP requests related to blog posts, ensuring secure and efficient data handling.
- **UserController.java**: Handles user-related HTTP requests, supporting functionalities like registration and profile management.

### Configuration Files
- **ApplicationConfig.java**: Central configuration class that manages application settings and beans.

## Setup and Configuration