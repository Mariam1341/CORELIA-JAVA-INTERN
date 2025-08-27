# CORELIA Internship Projects 🚀

This repository contains my projects and practical tasks for the **Java Internship Program at CORELIA**.

The repository will be continuously updated as I progress through the internship, learning new technologies and building more complex projects.

---

## 📅 **Internship Roadmap**

| Week | Main Topic                     | Project/Task                                          |
|------|--------------------------------|-------------------------------------------------------|
| 1    | Java Fundamentals & OOP        | ✅ Console-based Task Manager                         |
| 2    | Advanced Java & MySQL          | ✅ Pet Clinic Management System                       |
| 3    | Spring Boot Essentials         | ✅ Student Management API       |
| 4    | Spring Boot + JPA with MySQL   | ✅ Library Management System  |
| 5    | JSF Basics + Java Integration  | ✅ JSF Registration Form                                       |
| 6    | JSF + MySQL Integration        | ✅ Employee Directory Project                                    |
| 7    | Spring Boot + JSF + MySQL      | ✅ Attendance Tracking System  |
| 8    | Final Project                   | Coming Soon...                                       |

---

## ✅ **Completed Projects**

### 📦 Week 1 - Task Manager Console App

As part of the first week's assignment, I built a simple **Task Manager** using Java, applying core Object-Oriented Programming (OOP) concepts.

#### ✅ Features:
- Add new tasks  
- View all tasks  
- Filter tasks by status (TO DO, DOING, DONE)  
- Edit task description or status  
- Delete tasks  
- Input validation with graceful error handling  

#### 🛠️ Technologies:
- Java  
- Console-based interaction  
- Object-Oriented Programming (Encapsulation, Classes, Enums)  
- Defensive programming using custom input handling  

### 🎬 Demo Video

[![Task Manager Demo](https://img.youtube.com/vi/stTNWfBljO0/hqdefault.jpg)](https://youtu.be/stTNWfBljO0)

---

### 🐾 Week 2 - Pet Clinic Console App

This project is a **console-based Pet Clinic Management System** that performs full CRUD operations using **JDBC and MySQL**.

#### ✅ Features:
- Add a pet (name, type, age, owner name, owner phone)  
- View all pets  
- Search pets by name/type/owner  
- Update pet information  
- Delete pets  
- MySQL integration with a pre-defined schema  

#### 🛠️ Technologies:
- Java  
- JDBC  
- MySQL  
- Console-based interaction  
- Database connectivity  
- Clean separation between DAO and models

### 🎬 Demo Video

[![Pet Clinic Demo](https://img.youtube.com/vi/RQNuUSZU1zU/hqdefault.jpg)](https://youtu.be/RQNuUSZU1zU)

---

### 📚 Week 3 - Student Management API

This project is a **basic RESTful Student Management System**, developed using **Spring Boot** and **MySQL**.

#### ✅ Features:
- Create a new student  
- View all students  
- View student by ID  
- Update student information  
- Delete student  

#### 🛠️ Technologies:
- Java  
- Spring Boot  
- Spring Web  
- Spring JDBC  
- MySQL  
- RESTful API (GET, POST, PUT, DELETE)  
- Layered architecture (Controller, Service, Repository)

> ⚠️ **Note**: This version is kept simple and does **not** include DTOs, validation, or custom exception handling — the focus is on mastering core Spring Boot concepts first.

### 🎬 Demo Video

[![Student Managment Demo](https://img.youtube.com/vi/8E-93JOPBLI/hqdefault.jpg)](https://youtu.be/8E-93JOPBLI)

---

### 📚 Week 4 - Book & Author Management API (Spring Boot + JPA + DTOs)

This week I built a Book and Author Management System using Spring Boot, JPA, and MapStruct. The goal was to dive deeper into entity relationships and practice mapping between entities and DTOs.

### ✅ Features:
- Add / update / delete books and authors
- View all books or authors
- Search for books by title, category, or author
- Search for authors by name
- Proper entity relationships using **@ManyToOne** and **@OneToMany**
- Exception handling with custom global error responses
- Clean data transfer using **DTOs + MapStruct**
- Validation using `@Valid`, `@NotBlank`, etc.
- Layered architecture (Controller – Service – Repository)

### 🛠️ Technologies:
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- MapStruct
- Maven
- Lombok
- Javax Validation
- RESTful API Design
- Swagger

### 🧠 Key Learnings:
- Many-to-One & One-to-Many relationships with JPA
- How to use DTOs to clean up responses and requests
- Custom mapping logic using @Named in MapStruct
- Using @ComponentModel("spring") to integrate MapStruct with Spring

### 🎬 Demo Video

[![Book & Author Management Demo](https://img.youtube.com/vi/qZj2YvZiT_E/hqdefault.jpg)](https://youtu.be/qZj2YvZiT_E)

---

### 🌐 Week 5 - JSF Registration Form

This week, I built a basic registration form using JavaServer Faces (JSF) 2.2 to practice server-side rendering and form binding.

### ✅ Features:
- Simple registration form (name, email, password)
- Data binding to managed bean (request-scoped)
- Validation using JSF `<f:validate>` and required fields
- Feedback messages displayed with `<h:messages>`
- Simple success page after form submission

### 🛠️ Technologies:
- Java EE
- JSF 2.2 (Mojarra)
- XHTML (Facelets)
- Managed Beans (Java classes)
- Maven

### 🎬 Demo Video

[![Registration Form](https://img.youtube.com/vi/DSt91doPYWU/hqdefault.jpg)](https://youtu.be/DSt91doPYWU)

---

### 🌟 Employee Directory Project (JSF + JPA + MySQL)
This project is a web-based Employee Directory built using JavaServer Faces (JSF 2.2), JPA (Hibernate), and MySQL, deployed on Tomcat 9. It demonstrates a full CRUD (Create, Read, Update, Delete) application with clean layering between entity, DAO, service, and JSF managed bean.

### ✅ Features:
- List all employees in a data table
- Add new employees (name, email, department)
- Edit existing employee details
- Delete employees
- Server-side validation and data binding with JSF
- Persistence with JPA and MySQL database
- Simple and clean UI with JSF Facelets

### 🛠️ Technologies:
- Java 1.8
- JSF 2.2
- JPA (Hibernate)
- MySQL
- MySQL Connector
- Servlet API
- Tomcat 9
- Maven 

### 🎬 Demo Video

[![Employee Directory ](https://img.youtube.com/vi/aFl8a7sC_Ns/hqdefault.jpg)](https://youtu.be/aFl8a7sC_Ns)

---

### 🕒 Week 7 - Attendance Tracking System (JSF + PrimeFaces + MySQL)
This project is a web-based Attendance Tracking System built using **JSF 2.2**, **PrimeFaces**, and **MySQL**, deployed on Tomcat 9. It allows managing employee attendance with a modern UI.

### ✅ Features:
  - Add new attendance records
  - Edit existing attendance entries
  - Delete records
  - Mark presence/absence
  - Integrated calendar component for date selection
  - User-friendly PrimeFaces components and Growl notifications

### 🛠️ Technologies:
  - Java 1.8
  - JSF 2.2
  - PrimeFaces
  - JPA (Hibernate)
  - MySQL
  - Tomcat 9
  - Maven

### 🎬 Demo Video


---

## 👩‍💻 About Me

I'm currently interning at **CORELIA** in the Java Internship Program. I'm passionate about building solid Java backends, writing clean code, and exploring real-world applications using modern tools and design principles.

---


> ✨ Stay tuned — more projects and technologies are coming every week!


