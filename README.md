# Hospital Management System - Nurse Controller
This project is a **Java Spring Boot** application designed to manage a hospital's nursing staff. It leverages Hibernate for database interaction and includes a RESTful API to perform CRUD operations on Nurse entities.

## Features

- Nurse login authentication.
- Retrieve all nurses.
- Search nurse by name or ID.
- Add, update, and delete nurses.
- Data validation for password.

## API Endpoints

### Base URL: `/nurse`

1. **Login Nurse**  
   `POST /login`  
   - **Params**: `name`, `password`  
   - **Responses**:  
     - `200 OK`: Valid credentials.  
     - `401 Unauthorized`: Invalid credentials.

2. **Get All Nurses**  
   `GET /`  
   - **Response**: List of all nurses (`200 OK`).

3. **Find Nurse by Name**  
   `GET /name/{name}`  
   - **Response**:  
     - `200 OK`: Nurse found.  
     - `404 Not Found`: No nurse with the given name.

4. **Find Nurse by ID**  
   `GET /{id}`  
   - **Response**:  
     - `200 OK`: Nurse found.  
     - `404 Not Found`: No nurse with the given ID.

5. **Create New Nurse**  
   `POST /`  
   - **Params**: `name`, `password`, `age`, `speciality`  
   - **Response**:  
     - `201 Created`: Nurse added.  
     - `400 Bad Request`: Invalid input.

6. **Update Nurse**  
   `PUT /{id}`  
   - **Body**: `Nurse` (JSON)  
   - **Response**:  
     - `200 OK`: Nurse updated.  
     - `400 Bad Request`: Invalid input.  
     - `404 Not Found`: Nurse not found.

7. **Delete Nurse**  
   `DELETE /{id}`  
   - **Response**:  
     - `200 OK`: Nurse deleted.  
     - `404 Not Found`: Nurse not found.

## Data Model

### Nurse
```java
@Entity
public class Nurse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idNurse;
	private String name;
	private String password;
	private int age;
	private String speciality;
}
```

## Validation Rules

- **Password**: Must contain at least 6 characters, including 1 uppercase letter, 1 lowercase letter, and 1 number.

## Testing

- **JUnit** and **Mockito** are used for unit testing.
- Includes tests for all CRUD operations and login functionality.

Run tests locally using:
```bash
./mvnw test
```

### Automated Tests on Pull Requests
The project is configured with GitHub Actions to automatically run tests whenever a pull request is made to the main branch.

1. Create a pull request to ```master```.
2. GitHub Actions will automatically trigger the test workflow.
3. Review the test results directly in the pull request.

## Setup

1. Clone the project:
   ```bash
   git clone https://github.com/nsaladie/MP13-Hospital-BackEnd.git
   ```
2. Configure `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
