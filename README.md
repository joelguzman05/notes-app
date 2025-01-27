# Notes App 📓

A RESTful API application built with **Java 23**, **Spring Boot 3**, and **PostgreSQL**, providing users with secure note management features such as creating, archiving, and searching notes.

---

## 🏗️ Technologies Used

- **Backend:** Java 23, Spring Boot 3 (Spring Security, Spring Data JPA)
- **Database:** PostgreSQL 17
- **Security:** Spring Security + JWT
- **Build Tool:** Gradle
- **API Documentation:** Swagger (Springdoc OpenAPI)
  
---

## ⚙️ Installation and Setup

### Prerequisites

Ensure you have the following installed:

- Java 23
- PostgreSQL 14+

### Steps to Run the Project

1. Clone the Repository:
   ```
   git clone https://github.com/joelguzman05/notes-app.git
    ```
2. Configure the Application: Edit the `application.properties` file located in `src/main/resources/` with your database and security settings.:
3. Build and Run the Application:
   ```
   ./gradlew bootRun
   ```
4. Access the Application:
   - Swagger UI: http://localhost:8080/api/swagger-ui.html
   - API Docs: http://localhost:8080/api/v3/api-docs
     
