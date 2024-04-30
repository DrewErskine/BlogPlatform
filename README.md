# Blog Platform

## Description
This Blog Platform is a Spring Boot-based application designed to provide a simple yet robust platform for blogging. It supports operations to create, read, update, and delete blog posts, and it has been built using Java 21 and Spring Boot 3 to showcase modern Java development practices with a RESTful API design.

## Features
- **CRUD Operations**: Users can create, read, update, and delete blog posts.
- **Data Persistence**: Utilizes MySQL for reliable data storage.
- **RESTful API**: Includes endpoints for managing blog posts.
- **Basic Authentication**: Optional feature that can be implemented for user authentication.
- **Responsive Design**: Supports a responsive front end if implemented.

## Technologies
- **Spring Boot 3**: Framework for creating stand-alone, production-grade Spring-based applications.
- **Java 21**: Utilizes the latest features and security enhancements of Java.
- **MySQL**: Robust database management system for storing blog posts.
- **Maven**: Used for dependency management and project building.

## Getting Started

### Prerequisites
- Java 21 installed on your machine.
- gradlew installed for project build and dependency management.
- MySQL installed for the database.

### Setup Database
1. Install MySQL on your machine.
2. Create a new database named `blogdb` using the following SQL command:
   ```sql CREATE DATABASE blogdb;```

## Running the Application
1. Clone git
2. ``` cd plogplatform ```
3. Update the src/main/resources/application.properties file with your MySQL user and password.
4. clean application:
``` gradlew clean build ```
5. Run application:
``` ./gradlew bootRun ```


## API Endpoints

Method      Endpoint	        Description
GET         /api/posts	        Retrieves all blog posts.
GET	        /api/posts/{id}	    Retrieves a blog post by ID.
POST	    /api/posts	        Creates a new blog post.
PUT	        /api/posts/{id}	    Updates an existing blog post.
DELETE	    /api/posts/{id}	    Deletes a blog post.
