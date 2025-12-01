# Solution Steps

1. Define the RegisterUserRequest DTO with validation annotations for all required fields (email, password, fullName).

2. Define a UserResponse DTO with fields to be returned after successful registration.

3. Implement the User entity mapped to the 'users' table, including id, email, hashed password, and fullName.

4. Extend JpaRepository in UserRepository; provide a method to look up by email for duplicate prevention.

5. Implement the UserService containing registration logic: check for existing email, hash the password using BCrypt, store the user, asynchronously send the welcome email, and return the UserResponse DTO.

6. Define a WelcomeEmailService with an @Async method to simulate a slow welcome email operation.

7. Annotate the Application class with @EnableAsync to support background execution.

8. Implement the UserController POST /api/users/register endpoint that validates the request body and delegates to the UserService.

9. Create a GlobalExceptionHandler using @ControllerAdvice to handle validation errors, duplicate emails, and unexpected exceptions, and return structured error JSON with appropriate HTTP status codes.

10. Define an ApiError DTO to represent error responses, including details such as timestamp, HTTP status, message list, and path.

11. Ensure logging at key points (registration attempt, success, welcome email sending, error handling) for observability.

12. Verify that all layers (controller, service, repository) are cleanly separated following Spring Boot best practices.

