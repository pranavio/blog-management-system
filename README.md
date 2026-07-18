# 📝 Blog Management System

A production-ready **Blog Management System** built using **Spring Boot**, **Spring Security**, **JWT Authentication**, **Spring Data JPA**, **Hibernate**, and **MySQL**. This project follows a clean layered architecture and RESTful API design principles.

---

## 🚀 Features

### ✅ Authentication & Security
- User Registration
- User Login
- JWT Authentication
- Password Encryption using BCrypt
- Stateless Session Management
- Spring Security Integration
- Protected REST APIs

### 🚧 Upcoming Features
- CRUD Operations for Blog Posts
- Category Management
- Comment Management
- User Profile Management
- Role-Based Authorization (ADMIN, AUTHOR, USER)
- Global Exception Handling
- Request Validation
- Pagination & Sorting
- Search & Filtering
- Image Upload
- API Documentation using Swagger/OpenAPI

---

## 🛠️ Tech Stack

| Technology | Description |
|------------|-------------|
| Java 21 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT | Secure Token-Based Authentication |
| Spring Data JPA | Database Access |
| Hibernate | ORM Framework |
| MySQL | Relational Database |
| Maven | Dependency Management |
| Lombok | Boilerplate Code Reduction |

---

## 📂 Project Structure

```text
src
└── main
    ├── java
    │   └── com.example.blogkar
    │       ├── auth
    │       ├── category
    │       ├── comment
    │       ├── exception
    │       ├── post
    │       ├── security
    │       ├── testcontroller
    │       ├── user
    │       └── BlogkarApplication
    │
    └── resources
        ├── application.properties
        └── application-local.properties (ignored)
```

---

## 🔐 Authentication Flow

```text
Client
   │
   ▼
Authentication Controller
   │
   ▼
Authentication Service
   │
   ▼
Authentication Manager
   │
   ▼
Authentication Provider
   │
   ▼
Custom UserDetailsService
   │
   ▼
MySQL Database
   │
   ▼
Generate JWT Token
   │
   ▼
Return JWT to Client
```

---

## 📌 API Endpoints

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Authenticate user and generate JWT |

### Protected

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/test` | Test JWT Authentication |

---

## 📥 Sample Request

### Register

```json
{
  "fullName": "John Anderson",
  "email": "john.anderson@example.com",
  "password": "Admin@123"
}
```

### Login

```json
{
  "email": "john.anderson@example.com",
  "password": "Admin@123"
}
```

---

## ⚙️ Configuration

Sensitive configuration values are **not committed** to GitHub.

The project uses placeholders in `application.properties` and stores local secrets in:

```text
application-local.properties
```

Example:

```properties
DB_URL=jdbc:mysql://localhost:3306/blogmanagmentsystem
DB_USERNAME=root
DB_PASSWORD=your_password

JWT_SECRET=your_secret_key
```

This file is excluded from version control using `.gitignore`.

---

## ▶️ Running the Project

### Clone the Repository

```bash
git clone https://github.com/<your-username>/blog-management-system.git
```

### Navigate to Project

```bash
cd blog-management-system
```

### Configure Database

Create `application-local.properties` and add your database credentials.

### Run the Application

```bash
./mvnw spring-boot:run
```

or simply run `BlogkarApplication` from your IDE.

---

## 📅 Project Roadmap

- [x] Spring Boot Setup
- [x] MySQL Integration
- [x] Spring Security
- [x] JWT Authentication
- [x] User Registration
- [x] User Login
- [ ] Post CRUD
- [ ] Category CRUD
- [ ] Comment CRUD
- [ ] Role-Based Authorization
- [ ] Pagination & Sorting
- [ ] Swagger Documentation
- [ ] Docker Support
- [ ] Unit & Integration Testing

---

## 🤝 Contributing

Contributions, suggestions, and feedback are welcome.

1. Fork the repository
2. Create a new branch
3. Commit your changes
4. Open a Pull Request

---

## 📜 License

This project is licensed under the MIT License.

---

## 👨‍💻 Author

**Pranav**

Aspiring Java Backend Developer passionate about building scalable backend applications using Spring Boot and modern Java technologies.

---

⭐ If you found this project helpful, consider giving it a star!