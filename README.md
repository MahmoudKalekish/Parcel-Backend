# 📦 Parcel Delivery Backend API

A Spring Boot + MongoDB backend system to manage clients, parcels, and delivery status for a parcel delivery company.

Built with a RESTful architecture, JWT security, Swagger documentation, and tested using TDD principles.

---

## ✅ Features

### 1. Client Management
- Create a new client (first name, last name, email, phone)
- List all clients
- Search client by email

### 2. Parcel Management
- Create a parcel (sender ID, receiver ID, delivery address)
- Auto-generated ID and reference code
- Default status: `CREATED`
- Track status updates (`IN_TRANSIT`, `DELIVERED`, `CANCELLED`)
- Each update logged in a status history log with timestamps

### 3. Advanced Parcel Search
- Filter parcels by:
  - Current status
  - Sender ID
  - Creation date range

### 4. Authentication & Authorization
- JWT-based registration and login
- Role-based access to endpoints
- All `/api/**` endpoints are protected
- Public access to `/api/auth/**`, `/swagger-ui.html`, and `/v3/api-docs/**`

### 5. Documentation
- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- OpenAPI spec: `/v3/api-docs`

---

## 💻 Tech Stack

| Layer         | Tool                         |
|---------------|------------------------------|
| Language      | Java 21                      |
| Framework     | Spring Boot 3.4.4            |
| Database      | MongoDB                      |
| Auth          | Spring Security + JWT        |
| API Docs      | Springdoc OpenAPI (Swagger)  |
| Build Tool    | Maven                        |
| Tests         | JUnit + Mockito              |
| Deployment    | Docker            |

---

## 🚀 How to Run the Project

### 🔧 Option 1: Run Locally with Maven

#### Requirements:
- Java 17+
- MongoDB running on `localhost:27017`

#### Steps:
```bash
# Clone the project
git clone https://github.com/<your-username>/parcel-backend.git
cd parcel-backend

# Start MongoDB using Docker
docker-compose up -d


# Build and run
./mvnw clean install
./mvnw spring-boot:run
