# Spring Security Projects Overview
This project is designed to explore and practice **Spring Security** features.   
It contains following applications:

1. **Spring Boot MVC Application with secured pages and REST endpoints, and different users roles**
2. **Secret Providers**

## Project 1: Spring Boot MVC with REST Endpoints & Security

This project was implemented to practice security configuration for a Spring Boot MVC application.

### Features:
1. **REST Endpoints**:
    - `GET /about`: Public access.
    - `GET /info`: Restricted to users with "VIEW_INFO" permission.
    - `GET /admin`: Restricted to users with "VIEW_ADMIN" permission.

2. **Security**:
    - Spring Security is configured for email/password-based authentication.
    - Permissions control access to specific endpoints.
    - Custom Login/Logout pages.
    - Brute force protection with user blocking for 5 minutes after 3 failed login attempts.

More info in: [HELP.md](userAccessManagement/HELP.md)

## Project 2: Secret Provider

This application allows authenticated users to send and retrieve secrets through one-time access links.

### Features:
1. **Secret Sharing**:
    - A form for users to submit secrets, generating a unique link for one-time access.
    - After the secret is viewed, it is deleted from the system.   

More into in: [HELP.md](secretProvider/HELP.md)
