
---

# 📚 LMS Spring Boot API

## 📌 Overview

This project is a Learning Management System (LMS) REST API built using **Spring Boot**.
It provides functionality for managing users, courses, lessons, tests, comments, enrollments, and progress tracking.

The application follows a layered architecture with clear separation of concerns:

* Controller layer (REST endpoints)
* Service layer (business logic)
* Repository layer (database access)

---

## 🚀 Features

* User registration and authentication
* Role-based access (Admin, Teacher, Student)
* Course creation and management
* Lesson and test management
* Enrollment system
* Progress tracking
* Commenting system
* JWT-based authentication

---

## 🛠️ Tech Stack

* Java 17+
* Spring Boot
* Spring Security (JWT)
* Spring Data JPA (Hibernate)
* PostgreSQL
* MapStruct
* Lombok
* Docker (optional)

---

## 📂 Project Structure

```
src/main/java/com/example/Spring/LMS
│
├── controllers        # REST controllers
├── services          # Business logic
├── repositories      # Data access layer
├── entities          # JPA entities
├── dto               # Data Transfer Objects
├── mappers           # MapStruct mappers
├── security          # JWT + security config
└── enums             # Enums (roles, levels, etc.)
```

---

## 🔐 Authentication & Authorization

The application uses **JWT (JSON Web Tokens)** for authentication.

* Stateless authentication
* Token is passed in `Authorization: Bearer <token>`
* Role-based access control via annotations (`@PreAuthorize`)

---

## ⚙️ API Endpoints (Examples)

### Auth

```
POST /LMS/auth/register
POST /LMS/auth/login
```

### Courses

```
POST /courses
GET /courses/{id}
DELETE /courses/{id}
```

### Lessons / Tests / Comments

```
POST /courses/{courseId}/lessons
POST /tests
POST /comments
```

---

## 🧠 Documentation (Algorithms, Data Structures & Logic)

This section explains the core technical concepts used in the project.

### 📊 Data Structures

* **List** — used for storing collections (courses, lessons, comments)
* **Map (HashMap)** — used internally for fast lookups (e.g., roles, claims)
* **Entities (JPA)** — represent relational database tables
* **DTOs (Records)** — used for safe data transfer between layers

---

### ⚙️ Algorithms & Logic

#### 1. Authentication Flow

* User sends credentials
* System validates user via `UserDetailsService`
* JWT token is generated using secret key
* Token is parsed on each request
* User is authenticated via `SecurityContextHolder`

#### 2. Authorization Logic

* Based on roles (ADMIN, TEACHER, STUDENT)
* Implemented using:

    * `@PreAuthorize`
    * Security configuration rules

#### 3. Enrollment Check Algorithm

Before allowing actions (e.g., commenting), the system verifies:

```
IF user is enrolled in course
    allow action
ELSE
    throw exception
```

#### 4. Mapping Algorithm (MapStruct)

* Converts Entity → DTO
* Avoids exposing internal structure
* Automatically maps fields with same names

---

### 🧩 Core Modules

#### Users Module

Handles:

* Registration
* Login
* Role assignment

#### Courses Module

Handles:

* Course creation (Teacher/Admin)
* Course retrieval

#### Lessons Module

Handles:

* Lesson creation inside courses

#### Tests Module

Handles:

* Creating and managing tests

#### Comments Module

Handles:

* Adding comments (only enrolled users)

#### Progress Module

Tracks:

* Course completion status

---

## ⚠️ Challenges Faced

### 1. JWT Configuration

* Handling token validation
* Fixing weak key issues (needed ≥256-bit key)
* Correct parsing of claims

### 2. Spring Security Errors

* 403 errors despite valid token
* Misconfigured `SecurityFilterChain`
* Role-based access issues

### 3. JPA & Queries

* Non-unique results in queries
* Incorrect JPQL syntax
* Handling relationships (`@ManyToOne`, `@OneToMany`)

### 4. Mapping Issues

* Null fields in DTOs (MapStruct)
* Incorrect nested mappings

### 5. Docker & Database

* PostgreSQL connection issues
* Volume configuration errors

---

## 📈 Future Improvements

* Add integration tests
* Improve exception handling
* Add pagination & filtering
* Implement caching (Redis)
* Add frontend (React)

---

## 👨‍💻 Author

**Backend Developer focused on Java & Spring ecosystem.**

