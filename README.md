# 🌟 **User Registration and Authentication System**

## 🚀 **Overview**
A robust **User Registration and Authentication System** built using **Spring Boot**, with the following features:
- **User Registration** with OTP verification.
- **Authentication** with secure JWT tokens.
- **Password Reset** via email OTP.
- **Token Blacklisting** to invalidate sessions during password resets.
- **Redis Caching** for fast OTP storage and token management.

---

## 🎯 **Features**
- **Registration & Verification**: Users can register and confirm their accounts using an OTP sent to their email.
- **Secure Login**: Users can log in and receive JWT tokens for session management.
- **Logout**: Invalidate active tokens upon logout.
- **Password Reset**: Reset passwords securely by verifying OTPs.
- **Token Blacklisting**: Ensures no token reuse after sensitive actions like password resets.
- **Redis Integration**: Caches OTPs and manages blacklisted tokens efficiently.

---

## 🛠 **Technologies Used**
| **Technology**       | **Purpose**                              |
|-----------------------|------------------------------------------|
| **Java 17**           | Backend programming language            |
| **Spring Boot**       | Framework for building RESTful APIs     |
| **Spring Security**   | For authentication and authorization    |
| **H2 Database**       | In-memory database for testing          |
| **Redis**             | Caching OTPs and blacklisting tokens    |
| **Thymeleaf**         | Rendering HTML templates (optional)     |
| **AWS SES**           | Sending OTP emails                      |

---

## 🏗 **Setup Instructions**

### **Prerequisites**
- Install **Java 17** or higher.
- Install **Maven**.
- Start **Redis Server** on your system.
- (Optional) Install **Postman** for API testing.

### **Installation Steps**
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo-url/registration-auth-system.git
   cd registration-auth-system
   
### 2. Configure Database and Redis  
Update the database and Redis configurations in `application.properties`: 

```properties
# H2 Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update

# Redis Configuration
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.password=  # Leave empty if no password is set

3. Run Redis Server
Start the Redis server on the specified host and port.

4. Run the Application
Use the following command to start the application:
mvn spring-boot:run

5. Access H2 Console (Optional)
Navigate to http://localhost:8080/h2-console for in-memory database access.

6. Test the APIs
Use Postman to test the endpoints described below.

API Endpoints
User Registration
POST /user-registration/register
Description: Registers a new user and sends an OTP to their email.

Request Body:

json
{
  "name": "John Doe",
  "email": "johndoe@example.com",
  "password": "password123"
}
Response:

json
{
  "statusCode": "200",
  "statusMsg": "OTP sent to your email. Please verify within 5 minutes."
}
POST /user-registration/verifyOtp
Description: Verifies the OTP and completes registration.

Request Body:

json
{
  "email": "johndoe@example.com",
  "otp": "123456"
}
Response:

json
{
  "statusCode": "200",
  "statusMsg": "Registration successful. You can now login."
}
Login
POST /user-registration/login
Description: Authenticates a user and returns a JWT token.

Request Body:

json
{
  "email": "johndoe@example.com",
  "password": "password123"
}
Response:

json
{
  "statusCode": "200",
  "statusMsg": "eyJhbGciOiJIUzUxMiJ9..."
}
Logout
POST /user-registration/logout
Description: Invalidates the user's token.

Headers:

makefile
Authorization: Bearer <token>
Response:

json
{
  "statusCode": "200",
  "statusMsg": "Successfully logged out."
}
Forgot Password
POST /password-reset/forgot-password
Description: Sends an OTP to the user's email for password reset.

Request Params:

text
email=johndoe@example.com
Response:

json
{
  "statusCode": "200",
  "statusMsg": "OTP sent to email"
}
POST /password-reset/reset-password
Description: Verifies OTP and resets the user's password. Any active tokens are blacklisted.

Request Params:

text
email=johndoe@example.com  
otp=123456  
newPassword=newPassword123
Response:

json
{
  "statusCode": "200",
  "statusMsg": "Password reset successful, all sessions invalidated"
}
Token Blacklisting
Purpose
To invalidate all previously issued tokens when sensitive actions like password reset or account changes occur.

Implementation
Tokens are stored in Redis with a TTL equal to their expiration time.
During authentication, the token is checked against the blacklist.
If the token is blacklisted, access is denied.
Redis Key Structure
OTPs: OTP:<email>
Blacklisted Tokens: BLACKLISTED_TOKEN:<token_id>

Future Enhancements
Add email verification using AWS SES or Twilio.
Implement rate-limiting for sensitive endpoints like /login and /reset-password.
Add multi-factor authentication (MFA).

License
This project is licensed under the MIT License. Feel free to use and modify it as needed.
