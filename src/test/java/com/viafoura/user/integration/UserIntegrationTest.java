package com.viafoura.user.integration;

import com.viafoura.user.adapter.out.api.ReqresApiClient;
import com.viafoura.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ReqresApiClient reqresApiClient;

    private User user;

    @Override
    protected void setupTestData() {
        Mockito.when(reqresApiClient.fetchAvatarUrl(Mockito.anyInt()))
                .thenReturn("mock-avatar-url.jpg");

        User user = User.builder()
                .email("victor_pietro@hotmail.com")
                .firstName("Victor")
                .lastName("Reis")
                .build();

        this.user = userDataAccess.saveUser(user);
    }

    @Override
    protected void cleanupTestData() {
        userDataAccess.deleteAll();
    }

    @Nested
    @DisplayName("POST - /users")
    class CreateUser {

        @DisplayName("When user email already exist throws exception")
        @Test
        void whenCreatingNewUser_thenException() {
            String requestBody = """
                    {
                        "email": "victor_pietro@hotmail.com",
                        "firstName": "Victor",
                        "lastName": "Reis"
                    }
                    """;

            given()
                    .contentType("application/json")
                    .body(requestBody)
                    .when()
                    .post("/users")
                    .then()
                    .statusCode(409)
                    .body("message", is("User email already exist"));
        }

        @DisplayName("Success when creating new user")
        @Test
        void createUser() {
            String requestBody = """
                    {
                        "email": "victor_pietro+new@hotmail.com",
                        "firstName": "Victor",
                        "lastName": "Reis"
                    }
                    """;

            String locationHeader = given()
                    .contentType("application/json")
                    .body(requestBody)
                    .when()
                    .post("/users")
                    .then()
                    .statusCode(201)
                    .extract()
                    .header("Location");

            assertNotNull(locationHeader);
            assertTrue(locationHeader.contains("/users/"));
        }
    }

    @Nested
    @DisplayName("PUT - /users/{id}")
    class UpdateUser {

        @DisplayName("Successfully update user")
        @Test
        void successfulupdate() {
            String requestBody = """
                    {
                        "email": "victor_pietro+new@hotmail.com",
                        "firstName": "Victor",
                        "lastName": "Reis"
                    }
                    """.trim();
            Long userId = user.getId();

            given()
                    .contentType("application/json")
                    .pathParam("id", userId)
                    .body(requestBody)
                    .when()
                    .put("/users/{id}")
                    .then()
                    .statusCode(204);

            given()
                    .contentType("application/json")
                    .pathParam("id", userId)
                    .when()
                    .get("/users/{id}")
                    .then()
                    .statusCode(200);
        }

        @DisplayName("Successfully update user")
        @Test
        void successfulUpdateUser() {
            String requestBody = """
                    {
                        "email": "victor_pietro@hotmail.com",
                        "firstName": "VictorPietro",
                        "lastName": "Reis"
                    }
                    """.trim();
            Long userId = user.getId();

            given()
                    .contentType("application/json")
                    .pathParam("id", userId)
                    .body(requestBody)
                    .when()
                    .put("/users/{id}")
                    .then()
                    .statusCode(204);

            given()
                    .contentType("application/json")
                    .pathParam("id", userId)
                    .when()
                    .get("/users/{id}")
                    .then()
                    .statusCode(200);
        }

        @DisplayName("Throw exception if trying to update with existent email")
        @Test
        void updateFail() {
            User user = User.builder()
                    .email("victor_pietro+new+update@hotmail.com")
                    .firstName("Victor")
                    .lastName("Reis")
                    .build();
            user = userDataAccess.saveUser(user);

            String requestBody = """
                    {
                        "email": "victor_pietro@hotmail.com",
                        "firstName": "Victor",
                        "lastName": "Reis"
                    }
                    """.trim();
            Long userId = user.getId();

            given()
                    .contentType("application/json")
                    .pathParam("id", userId)
                    .body(requestBody)
                    .when()
                    .put("/users/{id}")
                    .then()
                    .statusCode(409)
                    .body("message", is("User email already in use"));
        }

    }

    @Nested
    @DisplayName("GET - /users")
    class GetUser {

        @DisplayName("Successfully retrieve a user")
        @Test
        void getUserSuccess() {
            given()
                    .contentType("application/json")
                    .pathParam("id", user.getId())
                    .when()
                    .get("/users/{id}")
                    .then()
                    .statusCode(200);
        }

        @DisplayName("Successfully retrieve all users")
        @Test
        void listUsersSuccess() {
            given()
                    .contentType("application/json")
                    .when()
                    .get("/users")
                    .then()
                    .statusCode(200)
                    .body("$", hasSize(greaterThan(0)))
                    .body("id", everyItem(notNullValue()))
                    .body("email", everyItem(containsString("@")));
        }

    }

    @Nested
    @DisplayName("DELETE - /users/{id}")
    class DeleteUser {

        @DisplayName("Successfully delete a user")
        @Test
        void successfulAdminDelete() {
            given()
                    .contentType("application/json")
                    .pathParam("id", user.getId())
                    .when()
                    .delete("/users/{id}")
                    .then()
                    .statusCode(204);

            given()
                    .contentType("application/json")
                    .pathParam("id", user.getId())
                    .when()
                    .get("/users/{id}")
                    .then()
                    .statusCode(404)
                    .body("message", is("User with id " + user.getId() + " not found"));
        }

    }

}
