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

---

### Service Files
- **LoginService.java**: Manages user authentication processes.
    - **Authenticate User**: Validates usernames and passwords.
    - **Load UserDetails**: Retrieves UserDetails for authenticated users to support security operations.

- **MyUserDetailsService.java**: Implements Spring Security's UserDetailsService to provide user details for authentication purposes.
    - **Load User Details**: Retrieves detailed user information, including roles and permissions, for security checks.

- **PostService.java**: Business logic for managing blog posts.
    - **Manage Posts**: Includes creating, updating, and deleting blog posts.
    - **Retrieve Posts**: Fetch posts with specific criteria.

- **UserService.java**: Manages user data and interactions.
    - **Register User**: Handles new user registrations.
    - **Update User Profile**: Updates information for existing users.
    - **Validation**: Ensures username and email uniqueness.

---

### Repository Files
- **PostRepository.java**: Extends JpaRepository for CRUD operations on blog posts, enhanced with custom queries.
    - **Find by Title**: Searches posts by title, ignoring case.
    - **Find by Content**: Allows searching within the content of posts.
    - **List All Posts by Creation Date**: Retrieves all posts ordered by their creation timestamp.

- **UserRepository.java**: Manages database operations for user entities.
    - **Check Existence by Username/Email**: `existsByUsername(String username)`, `existsByEmail(String email)`
    - **Find by Username/Email**: Includes methods to retrieve users by their username or email.
    - **Custom User Queries**:  Facilitates fetching users with specific parameteres

---

### Model Files
- **Authority.java**: Defines security roles for users.
    - **Role Name**: Identifies the security role or authority.

- **Post.java**: Entity model representing a blog post.
    - **ID**: Auto-generated unique identifier.
    - **Title**: Title of the post.
    - **Content**: Content body of the post.
    - **Created At**: Timestamp of when the post was made.
    - **Author ID**: Reference to the user who created the post.

- **User.java**: Entity model for managing users.
    - **ID**: Auto-generated unique identifier.
    - **Username**: Unique username for the user.
    - **Email**: Email address used for user identification and communications.
    - **Password**: Encrypted password for securing user login.
    - **Authorities**: Roles or permissions assigned to the user.

---

### Controller Files
- **BlogController.java**: Manages views related to blog interactions.
    - **Show Blog Form**: GET `/blog`
    - **Blog Home Page**: GET `/blogHome`

- **HomeController.java**: Serves the main entry page of the application.
    - **Home Page**: GET `/home`

- **LoginController.java**: Handles the display of the login page.
    - **Login Form**: GET `/login`

- **PostController.java**: Controls the management of blog posts, including listing, viewing, adding, and removing posts.
    - **List All Posts**: Provides an interface to fetch all posts.
    - **Retrieve a Post**: Allows fetching a specific post by ID.
    - **Create a New Post**: Handles the creation of new posts.
    - **Delete a Post**: Facilitates the deletion of existing posts.

- **RegisterController.java**: Oversees user registration, providing both the interface and the functionality for new users to register.
    - **Show Registration Form**: GET `/register`
    - **Register User**: POST `/register`
    - **Registration Success/Failure**: Redirects and handles post-registration flow.

- **UserController.java**: Manages user-specific functionalities, particularly focusing on user profiles.
    - **Display User Profile**: GET `/api/user/profile/{userId}`
    - **Edit Profile**: GET `/api/user/edit-profile`
    - **Update Profile**: POST `/api/user/edit-profile`

---

### Config
- **ApplicationConfig.java**: Central configuration class that manages application settings and beans.
    - **Data Source Configuration**: Configures a `DataSource` bean using properties for database connectivity.
    - **Entity Manager Factory**: Sets up a `LocalContainerEntityManagerFactoryBean` with a `HibernateJpaVendorAdapter`.
    - **Transaction Manager**: Configures a `JpaTransactionManager` for managing JPA transactions.
    - **JPA and Entity Scanning**: Activates Spring Data JPA repositories and transactional support, scans for entity definitions in the specified packages.

- **AuthConfig.java**: Manages security configurations for authentication.
    - **BCryptPasswordEncoder**: Configures a `BCryptPasswordEncoder` for secure password hashing.

- **CsrfTokenAdvice.java**: Provides CSRF token configuration across the application.
    - **CSRF Token Integration**: Automatically makes CSRF tokens available as model attributes in all views.

- **WebConfig.java**: Handles web configuration and resource management.
    - **Thymeleaf Setup**: Configures `ThymeleafViewResolver` and `SpringTemplateEngine` for managing views.
    - **Static Resources**: Manages the serving of static resources like CSS and JavaScript.

- **WebSecurityConfig.java**: Configures security aspects for the application.
    - **CSRF Protection**: Utilizes `CookieCsrfTokenRepository` with HTTP-only set to false.
    - **Session Management**: Adopts a policy to create sessions as required.
    - **Authorization**: Ensures static resources and specific pages like login and registration are publicly accessible, while all other requests require authentication.
    - **Form Login and Logout**: Configures paths for form login and logout, handles successful and unsuccessful authentication attempts, manages cookies, and session invalidation.
