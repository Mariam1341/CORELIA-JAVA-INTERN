## 📚 Library Management System – Spring Boot Project

A Java-based backend project for managing authors and books in a simple library system.

### ✅ What I Did:
- Built complete **CRUD REST APIs** for `Author` and `Book` entities.
- Used **DTO Pattern** to separate internal models from API contracts.
- Integrated **MapStruct** for clean and automatic object mapping between Entities and DTOs.
- Designed a **One-to-Many relationship**: One Author ➝ Many Books.
- Implemented **custom logic in MapStruct** to convert between Book objects and their titles.
- Created a **Global Exception Handler** using `@ControllerAdvice` for consistent error responses.
- Added **Field Validation** using `@Valid`, `@NotBlank`, `@Email`, etc.
- Used **Lombok** to reduce boilerplate code (getters, setters, builders).
- Tested the entire project using **Swagger**.

### 🔧 Technologies Used:
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- MapStruct
- Lombok
- Maven
- Swagger

### 🎯 Focus Areas:
- Clean Architecture & Best Practices
- DTO + Entity Mapping
- Exception Handling
- Validation & Input Safety
