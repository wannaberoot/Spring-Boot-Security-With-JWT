# Spring-Boot-Security-With-JWT
Spring Boot Security application using JWT.

## Technologies used in this project.
1. Spring Security
2. JWT
3. Liquibase
4. PostgreSQL
5. Lombok

## What is Spring Security?
Spring security provides authentication and authorization to our application using simple servlet filters. Web applications are susceptible to security threats and attacks, as they are accessible by anyone uses the internet. There may exist some REST endpoints having restricted access to specific users, for example, updating records or admin related operations.

## How to Run?
1. Download and install PostgreSQL.
```
https://www.postgresql.org/download/
```

2. Create "auth" database.
```
create database auth;
```

3. After running the application, you can go to the Swagger.
```
http://localhost:8080/swagger-ui/index.html
```

## After Running
1. You can create a user via "/api/user" endpoint.
![---](/images/image1.png)

2. After creation of user, you should authenticate and get token using your username and password via "/api/authenticate" endpoint.
![---](/images/image2.png)

3. After get your access token, you can run all of "Item" operations.
![---](/images/image3.png)
