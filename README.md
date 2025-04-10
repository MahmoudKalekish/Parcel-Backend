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
- Java 21
- MongoDB running on `localhost:27017`


#### Steps:
# Clone the project
```git clone https://github.com/MahmoudKalekish/Parcel-Backend.git```

  ```cd parcel-backend```

# Start MongoDB using Docker
```docker-compose up -d```


# Build and run
./mvnw clean install
./mvnw spring-boot:run

# ✅ Step-by-Step Postman Testing (as curl)
## 🔐 1. Register User
```curl --location 'http://localhost:8080/api/auth/register' \
--header 'Content-Type: application/json' \
--data '{
  "username": "mahmoud",
  "password": "test123"
}'
```
## 🔐 2. Login User (Get JWT Token)
```curl --location 'http://localhost:8080/api/auth/login' \
--header 'Content-Type: application/json' \
--data '{
  "username": "mahmoud",
  "password": "test123"
}'

```
### ✅ Copy the token from the JSON response.

## ✅ 3. Create a Client
```curl --location 'http://localhost:8080/api/clients' \
--header 'Authorization: Bearer <PASTE_YOUR_TOKEN_HERE>' \
--header 'Content-Type: application/json' \
--data '{
  "firstName": "Ali",
  "lastName": "Kalakech",
  "email": "ali.kalakech@example.com",
  "phone": "9613000000"
}'

```

## ✅ 4. List All Clients
```curl --location 'http://localhost:8080/api/clients' \
--header 'Authorization: Bearer <PASTE_YOUR_TOKEN_HERE>'

```

## ✅ 5. Search Client by Email
```curl --location 'http://localhost:8080/api/clients/search?email=ali.kalakech@example.com' \
--header 'Authorization: Bearer <PASTE_YOUR_TOKEN_HERE>'

```

## 📦 6. Create a Parcel
### Assume you got 2 client IDs (sender and receiver) from previous steps.
```curl --location 'http://localhost:8080/api/parcels' \
--header 'Authorization: Bearer <PASTE_YOUR_TOKEN_HERE>' \
--header 'Content-Type: application/json' \
--data '{
  "senderId": "CLIENT_ID_1",
  "receiverId": "CLIENT_ID_2",
  "deliveryAddress": "Beirut, Lebanon"
}'

```

## 📦 7. Update Parcel Status
```curl --location --request PUT 'http://localhost:8080/api/parcels/{PARCEL_ID}/status?status=IN_TRANSIT' \
--header 'Authorization: Bearer <PASTE_YOUR_TOKEN_HERE>'

```

## 🔍 8. Search Parcels by Status
```curl --location 'http://localhost:8080/api/parcels/status?status=IN_TRANSIT' \
--header 'Authorization: Bearer <PASTE_YOUR_TOKEN_HERE>'

```

## 🔍 9. Search Parcels by Sender ID
```curl --location 'http://localhost:8080/api/parcels/sender?senderId=CLIENT_ID_1' \
--header 'Authorization: Bearer <PASTE_YOUR_TOKEN_HERE>'

```

## 🔍 10. Search Parcels by Date Range
```curl --location 'http://localhost:8080/api/parcels/date?from=2024-04-01T00:00:00&to=2025-05-01T23:59:59' \
--header 'Authorization: Bearer <PASTE_YOUR_TOKEN_HERE>'

```


![image](https://github.com/user-attachments/assets/38b61e4d-b51e-40ec-989c-60f09ce57d77)
