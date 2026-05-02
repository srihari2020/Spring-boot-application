# Library Management System

A professional, full-stack Library Management System built with **Spring Boot 3.2**, **Spring Data JPA**, and **JSP**. This application allows users to manage a collection of books and authors with full CRUD functionality, keyword search, and genre-based filtering.

## 🚀 Features

- **Full CRUD for Books & Authors**: Create, Read, Update, and Delete both entities.
- **Dynamic Search**: Search for books by title using keyword matching.
- **Genre Filtering**: Filter the book collection by categories (Fantasy, Mystery, Horror, etc.).
- **Auto-Seeding**: The database is automatically populated with 10 Authors and 10 Books on startup for immediate testing.
- **Data Integrity**: Robust exception handling for database constraints (e.g., preventing deletion of authors who have existing books).
- **Responsive UI**: Styled with modern CSS, featuring a persistent navigation bar and interactive badges.

## 🛠️ Tech Stack

- **Backend**: Java 17, Spring Boot 3.2.0, Spring Data JPA
- **Database**: H2 In-Memory Database
- **View Engine**: JSP (Jakarta Server Pages) with JSTL 2.0
- **Styling**: Vanilla CSS3
- **Utilities**: Lombok, Jakarta Validation

## 📋 Prerequisites

- **Java Development Kit (JDK)**: Version 17 or higher
- **Apache Maven**: Version 3.8 or higher
- **Web Browser**: Chrome, Firefox, or Edge

## 📥 How to Clone and Run

### 1. Clone the Repository
Open your terminal or command prompt and run:
```bash
git clone https://github.com/VemireddyBhavana/library-management.git
```

### 2. Navigate to the Project
```bash
cd library-management/library-management
```

### 3. Run the Application
Use Maven to start the Spring Boot server:
```bash
mvn spring-boot:run
```

### 4. Access the UI
Once the console shows that the application has started, open your browser and go to:
[http://localhost:8080](http://localhost:8080)

---

## 🔍 Database Management
Since the application uses an **H2 In-Memory Database**, you can access the database console at:
- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:librarydb`
- **Username**: `sa`
- **Password**: *(leave blank)*

## 📂 Project Structure
- `com.library.entity`: JPA Data Models (Author, Book)
- `com.library.repository`: Spring Data JPA Repositories
- `com.library.service`: Business Logic Layer
- `com.library.controller`: Web Controllers for Routing
- `src/main/webapp/WEB-INF/views`: JSP templates for the front-end

---
Developed as a final project for Library Management System.
