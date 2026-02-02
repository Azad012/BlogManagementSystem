📝 Blog Management System – Spring Boot Backend

A RESTful Blog Management System built using Spring Boot, providing secure user authentication and full CRUD operations for blog posts and comments. The project follows clean architecture, DTO pattern, and JWT-based authentication, making it suitable for real-world production use.

🚀 Features
🔐 Authentication & Authorization

User Registration & Login

JWT Token–based Authentication

Role-based access control

Secure password encryption (BCrypt)

📝 Blog Management

Create, Read, Update, Delete (CRUD) blog posts

Only authors can update/delete their own posts

Public access to view posts

💬 Comment Management

Add comments to blog posts

Edit & delete own comments

View comments for a post

👤 User Profile

View logged-in user profile

Fetch user’s own posts

⚙️ Technical Features

RESTful APIs

DTO-based request/response handling

Global exception handling

Pagination & sorting

MySQL database integration

🛠️ Tech Stack
Technology	Description
Java	Java 17
Spring Boot	Backend framework
Spring Security	Authentication & Authorization
JWT	Token-based security
Spring Data JPA	ORM
MySQL	Database
Maven	Dependency management
Lombok	Boilerplate reduction
📁 Project Structure
blog-management-system
│
├── controller
│   ├── AuthController.java
│   ├── PostController.java
│   ├── CommentController.java
│
├── service
│   ├── AuthService.java
│   ├── PostService.java
│   ├── CommentService.java
│
├── repository
│   ├── UserRepository.java
│   ├── PostRepository.java
│   ├── CommentRepository.java
│
├── entity
│   ├── User.java
│   ├── Post.java
│   ├── Comment.java
│
├── dto
│   ├── LoginRequest.java
│   ├── RegisterRequest.java
│   ├── PostRequest.java
│   ├── PostResponse.java
│
├── security
│   ├── JwtUtil.java
│   ├── JwtFilter.java
│   ├── SecurityConfig.java
│
├── exception
│   ├── GlobalExceptionHandler.java
│
├── BlogManagementApplication.java
└── application.properties

🔧 Configuration
application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/blog_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=your_secret_key
jwt.expiration=86400000

▶️ How to Run the Project
Prerequisites

Java 17+

MySQL

Maven

Steps
git clone https://github.com/your-username/blog-management-system.git
cd blog-management-system
mvn clean install
mvn spring-boot:run


Application will start at:

http://localhost:8080

🔑 API Endpoints (Sample)
Auth APIs
Method	Endpoint	Description
POST	/api/auth/register	Register user
POST	/api/auth/login	Login & get JWT
Post APIs
Method	Endpoint
GET	/api/posts
POST	/api/posts
PUT	/api/posts/{id}
DELETE	/api/posts/{id}
Comment APIs
Method	Endpoint
POST	/api/comments/{postId}
PUT	/api/comments/{id}
DELETE	/api/comments/{id}
🔐 Authentication Header
Authorization: Bearer <JWT_TOKEN>

🧪 Testing

Use Postman or Swagger

Validate JWT for protected APIs

Test role-based access

🌐 Frontend Integration

This backend is designed to integrate seamlessly with:

React.js

Axios

JWT stored in localStorage

Protected Routes

📌 Future Enhancements

Like / Dislike posts

User roles (ADMIN / USER)

Swagger API documentation

Docker deployment

Cloud hosting (AWS / Render)

👨‍💻 Author

Azad Ansari
Backend Developer – Spring Boot
