# Authentication and Authorization with JWT in Spring Framework 6 using Hexagonal Architecture

This project aims to perform user authentication and authorization using JsonWebToken using Spring Boot 3 & 
Spring Security 6 using Hexagonal Architecture to make this project easy to grow. Also, it uses Lombok library for
dependency injection and Mapstruct to map objects between infrastructure layer and application layer.

## How to run ▶️
* Run `docker-compose up -d` on root project
* Create a database named `login_db`
* If you want to use `qa` profile, create a database named `qa_db`. The tables of this database are defined in `src/main/resources/qa/schema.sql`
* Create all the tables inside the database using the querys in `<project_root>/SQL_tables_querys.txt` file
* Run `./mvnw spring-boot:run` on root project

## Features 🔬
### General
* Hexagonal Architecture
* Different entities in infrastructure layer and application layer
* Lombok for dependency injection
* All endpoints covered with integration tests
* Spring Security 6
* Exception handling with `@ControllerAdvice`
* Docker compose file for the database
* Tokens expiration is configurable in application.yml file
* Simple API

### Endpoints
* Public
    * POST `/api/auth/register`
        * Body:
      ```
      {
          "enmail":string,
          "username":string,
          "password":string
      }
      ```
        * Response
            * `201` if user is new
            * `400` if body is null or contains illegal characters
            * `409` if user with provided email or username exists in DB
    * POST `/api/auth/login`
        * Body:
      ```
      {
          "username":string,
          "password":string
      }
      ```
        * Response
            * `200` if user and password are correct
                * Body: `{"userId":UUID,"accesssToken":string,"sessionExpiration":number}`
            * `400` if body is null or contains illegal characters
            * `401` if user and password are not correct
* Authenticated user with ROLE_USER
    * GET `/api/user`
        * Response
          * `200` if user is authorized 
            * Body: `Hello <username>!`
          * `401` if user is not authenticated
          * `403` if user is not authorized
* Authenticated user with ROLE_ADMIN
    * GET `/api/admin`
        * Response
            * `200` if user is authorized
                * Body: `Hello <username>, you are ADMIN!`
            * `401` if user is not authenticated
            * `403` if user is not authorized