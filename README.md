# Spring Boot Applications Collection

This repository contains two complete Spring Boot applications developed for academic assignments.

## 📁 Projects

### 1. [Student & Course Management System](./student-course-app)
A management system for tracking students and their enrolled courses.
- **Entities**: Student, Course
- **Relationship**: Many-to-One (Students to Course)
- **Features**: Create, Read, Update students with custom inner join queries.
- **Tech Stack**: Java 17, Spring Boot 3.2, Spring Data JPA, H2, JSP.

### 2. [Library Management System](./library-management-repo/library-management)
A comprehensive system for managing books and authors.
- **Entities**: Book, Author
- **Relationship**: Many-to-One (Books to Author)
- **Features**: Full CRUD, search by title, and genre filtering.
- **Tech Stack**: Java 17, Spring Boot 3.2, Spring Data JPA, H2, JSP, Lombok.

## 🚀 How to Run

Both projects include a **Maven Wrapper** and a custom **PowerShell start script** to ensure they run correctly even if Java is not fully configured in your environment.

### To run the Student & Course Management System:
1. Open PowerShell.
2. Navigate to the project: `cd student-course-app`
3. Run: `.\start.ps1`

### To run the Library Management System:
1. Open PowerShell.
2. Navigate to the project: `cd library-management-repo/library-management`
3. Run: `.\start.ps1`

## 🛠️ Requirements
- **Java 17+** (Installed automatically during setup if missing).
- **Web Browser** for accessing the UI at `http://localhost:8080`.
