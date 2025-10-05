# OnlineQuizBackend
Online Quiz Application - Backend (Spring Boot)

This is the backend API for an Online Quiz Application, built using Java Spring Boot. It provides RESTful endpoints for user management, quiz creation, participation, scoring, and results tracking.

## Technologies Used

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **MySQL** (Configurable)(if not using H2)
- **Maven**
- **Lombok**
- **Postman**(for Testing)


## Project Structure

src/
└── main/
├── java/com/sarita/onlinequiz/
│ ├── controller/
│ ├── dto/
│ ├── entities/
│ ├── repository/
│ ├── service/
│ └── OnlinequizApplication.java
└── resources/
   ├── application.properties

## Getting Started

### Prerequisites

Make sure you have the following installed:

- Java JDK (version 17+ recommended)
- Maven
- A relational database (MySQL, PostgreSQL, or H2 for dev)
- Postman for testing

## Setup & Installation

1.Clone the repository

git clone https://github.com/SaritaN30/OnlineQuizBackend.git
cd OnlineQuizBackend

2.Configure application properties

spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabasename
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

3.Build and Run
(If maven is installed)
mvn clean install
mvn spring-boot:run

Alternative: Use Maven from IntelliJ / VS Code
If you’re using an IDE like IntelliJ IDEA or VS Code, and Maven is bundled or managed by the IDE:
You can build the project directly inside the IDE (right-click project → Maven → Lifecycle → install)
You don't necessarily need to run mvn manually in Git Bash

4.Access the API
By default it should run on http://localhost:8080/ (unless you changed the port.For changing the port in application.properties file add server.port=yourportnumber).


API Endpoints Overview
Auth
Method	Endpoint					Description
POST	/api/auth/signUp			Register a new user
POST	/api/auth/login				Login of a user

Admin Endpoints
Method	Endpoint					Description
POST	/api/test					Create a new quiz test
POST	/api/test/addQuestion		Adding questions to a created test
GET	    /api/test					For fetching all test
GET	    /api/test/{id}				Get all questions of a specific test
GET	    /api/test/testResult	    Find all test results

User Endpoints
Method	Endpoint					Description
	
POST	/api/test/submitTest		Submit answers
GET	    /api/test/testResult/{id}	Find all test results of a specific user id


