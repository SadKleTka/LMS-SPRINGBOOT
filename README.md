# 🎓 Spring Boot LMS (Learning Management System)

A RESTful Learning Management System built with **Spring Boot**.\
This project demonstrates backend development skills including
authentication, role-based access control, database design, and clean
architecture.

------------------------------------------------------------------------

## 🚀 Tech Stack

-   **Java 24**
-   **Spring Boot**
-   **Spring Security (JWT Authentication)**
-   **Spring Data JPA**
-   **PostgreSQL**
-   **Hibernate**
-   **Maven**
-   **Lombok**
-   **Docker (optional)**

------------------------------------------------------------------------

## 📌 Features

### 👤 Authentication & Authorization

-   User registration
-   Login with JWT
-   Role-based access control (ADMIN / TEACHER / STUDENT)
-   Secure endpoints with Spring Security

### 📚 Course Management

-   Create, update, delete courses (ADMIN / TEACHER)
-   View available courses (all authenticated users)
-   Assign users to courses

### 📝 Lessons & Content

-   Add lessons to courses
-   Retrieve lessons by course
-   Structured course content

### 📊 Database Design

-   Proper entity relationships (One-to-Many, Many-to-Many)
-   Clean layered architecture:
    -   Controller
    -   Service
    -   Repository
    -   DTO
    -   Security layer

------------------------------------------------------------------------

## 🏗 Architecture

The project follows a clean REST architecture:

Controller → Service → Repository → Database

Security is handled using JWT filters integrated into Spring Security.

------------------------------------------------------------------------

## ⚙️ How to Run

### 1️⃣ Clone the repository

``` bash
git clone https://github.com/your-username/your-repository.git
cd your-repository
```

### 2️⃣ Configure database

Update `application.yml` (or `application.properties`) with your
PostgreSQL credentials:

``` yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/lms
    username: your_username
    password: your_password
```

### 3️⃣ Run the application

``` bash
mvn clean install
mvn spring-boot:run
```

Application will start on:

    http://localhost:8080

------------------------------------------------------------------------

## 🔐 Example API Endpoints

  Method   Endpoint              Description
  -------- --------------------- ------------------------------
  POST     /auth/register        Register new user
  POST     /auth/login           Authenticate and receive JWT
  GET      /courses              Get all courses
  POST     /courses/create       Create new course
  GET      /lessons/{courseId}   Get lessons by course

------------------------------------------------------------------------

## 📂 Project Structure

    src
     ├── controller
     ├── service
     ├── repository
     ├── security
     ├── dto
     └── entity

------------------------------------------------------------------------

## 💡 What This Project Demonstrates

-   Practical use of Spring Security with JWT
-   Understanding of REST API design
-   Clean code structure and separation of concerns
-   Real-world backend architecture
-   Database relationship modeling

------------------------------------------------------------------------

## 🧠 Future Improvements

-   Pagination & filtering
-   Swagger/OpenAPI documentation
-   Unit & integration tests
-   Docker Compose setup
-   File upload support

------------------------------------------------------------------------

## 👨‍💻 Author

Backend Developer focused on Java & Spring ecosystem.

------------------------------------------------------------------------

⭐ If you like this project, feel free to star the repository!
