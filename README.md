# Booking App
### Description
Booking App allows you to register new users and book hotel rooms.
It also allows the property to be managed by an administrator.
Information about user details and assigned bookings are available for management.
Each user can change their data in "My Profile".
### Project on heroku
This project is available on the heroku platform at: https://booking-app-spring.herokuapp.com/
### Authentication
Admin account:
* username: admin
* email: admin@gmail.com
* password: admin

User account:
* username: user
* email: user@gmail.com
* password: user

The admin account has more privileges than other accounts.
You can always register your own user!
### Database layer
The PostgreSQL database was used in this project.
Created tables: user, booking.
Relations between tables:
* user OneToMany booking

### Back-end layer
Technologies used:
* Java 11
* Spring Boot 2.4.0
* Spring Data JPA
* PostgeSQL Driver
* Spring Web
* Spring Security
* Lombok
* Maven
### Front-end layer
Technologies used:
* HTML5
* CSS3
* JavaScript
* Thymeleaf
* Bootstrap 4
* jQuery
