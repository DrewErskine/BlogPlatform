# Blog Platform

## Description
This Blog Platform is a Spring Boot-based application designed to provide a simple yet robust blogging platform. It allows users to create, read, update, and delete blog posts. Built with Java 21 and Spring Boot 3, this application showcases modern Java development practices with RESTful API design.

## Features
- Create, read, update, and delete blog posts.
- Data persistence with MySQL.
- RESTful API endpoints.
- Basic authentication (optional implementation).
- Responsive design (if frontend is implemented).

## Technologies
- **Spring Boot 3**: Framework for creating stand-alone, production-grade Spring based Applications easily.
- **Java 21**: Latest features and security enhancements of Java.
- **MySQL**: Reliable and robust database for storing blog posts.
- **Maven**: Dependency management and project build tool.

## Getting Started

### Prerequisites
- Java 21
- Maven
- MySQL

### Setup Database
1. Install MySQL on your machine.
2. Create a new database named `blogdb`:
   ```sql
   CREATE DATABASE blogdb;

## Running the Application
1. Clone
``` git clone https://github.com/yourusername/blog-platform.git ```
2. 
``` cd plog-platform ```
3. Update the src/main/resources/application.properties file with your MySQL user and password.
4. Run the application:
``` mvn spring-boot:run ```
5. Testing
``` mvn test ```

## API Endpoints


Method      Endpoint	        Description
GET         /api/posts	        Retrieves all blog posts.
GET	        /api/posts/{id}	    Retrieves a blog post by ID.
POST	    /api/posts	        Creates a new blog post.
PUT	        /api/posts/{id}	    Updates an existing blog post.
DELETE	    /api/posts/{id}	    Deletes a blog post.
