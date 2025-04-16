# Car Rental System

> **Car Rental System** is a full-stack web application designed for managing car rental operations.
> It provides a secure and user-friendly platform where **admins** can publish available cars for rent and **users** can browse and rent them.
> The application features role-based access control, email verification using **MailHog**, and secure password encryption with **PBKDF2**.
> Built with **Spring Boot**, **Spring Security**, **MySQL**, and for frontend **HTML, CSS, and JavaScript**.

# Features

###  User Features
- Register and activate account via email confirmation (MailHog).
- Login and logout securely with Spring Security.
- Browse available cars.
- Rent cars from the available publications.

### 🛠 Admin Features
- Secure admin login.
- Create, update, and delete car rental publications.

###  Security
- Role-based access control (User/Admin).
- Passwords are hashed using **PBKDF2** (`Pbkdf2PasswordEncoder`).
- Email-based account activation via secure token.

---

##  Technologies Used

- **Backend**: Spring Boot, Spring MVC, Spring Data JPA, Spring Security
- **Frontend**: HTML, CSS, JavaScript
- **Database**: MySQL
- **Email Service**: MailHog (for testing)
- **Build Tool**: Gradle
- **Password Encoder**: `Pbkdf2PasswordEncoder`

---
