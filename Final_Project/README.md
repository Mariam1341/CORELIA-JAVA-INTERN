# Task Management System

[![Java](https://img.shields.io/badge/Java-1.8-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.0-brightgreen)](https://spring.io/projects/spring-boot)
[![JSF](https://img.shields.io/badge/JSF-2.2-orange)](https://jakarta.ee/specifications/faces/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-green)](LICENSE)

A **Self-Assigned Task Management System** built using **Java, JSF, Spring Boot, and MySQL**.  
Users can create, manage, and track their own tasks with a simple and responsive web interface.

---

## 🛠️ Technologies Used
- **Backend:** Java 1.8, Spring Boot, JPA/Hibernate
- **Frontend:** JSF, PrimeFaces, Bootstrap 5
- **Database:** MySQL
- **Build Tool:** Maven
- **Deployment:** JAR (Spring Boot embedded Tomcat)
- **Charts & Reporting:** PrimeFaces Charts (Pie & Bar)

---

## ⚡ Features
### Core Features
- User registration and authentication
- Self-assigned task creation and management
- Task statuses: TODO, IN_PROGRESS, DONE
- Pie Chart: Tasks by Status
- Bar Chart: Tasks by User (number of tasks)
- DataTable for all tasks with pagination
- Logout functionality

### UI/UX Enhancements
- Sidebar navigation with active page highlighting
- Bootstrap badges for task status coloring
- Custom error page for unexpected issues

---

### 📊 Reporting

Tasks by Status: Pie Chart showing TODO, IN_PROGRESS, DONE tasks with custom colors:

TODO → Secondary (Gray)

IN_PROGRESS → Warning (Yellow)

DONE → Success (Green)

Tasks by User: Bar Chart showing number of tasks per user

All Tasks Table: Paginated list of all tasks with status badges in the same color scheme
