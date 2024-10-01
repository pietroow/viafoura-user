package com.viafoura.user.controller;

import com.viafoura.user.port.application.UserManagementService;
import com.viafoura.user.port.in.ManageUserUseCase;
import com.viafoura.user.port.in.web.UserController;
import com.viafoura.user.port.in.web.dto.UserUpdateRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends BaseControllerTest {

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        ManageUserUseCase userManageUseCase = mock(UserManagementService.class);
        this.userController = new UserController(userManageUseCase);
        registerController(this.userController);
    }

    @Nested
    class CreateUserRequestDTOTest {

        @Test
        void shouldReturnBadRequestWhenEmailIsBlank() throws Exception {
            UserUpdateRequestDTO userCreate = new UserUpdateRequestDTO(
                    "",
                    "Victor",
                    "Reis"
            );

            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(userCreate)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenEmailIsNotEmail() throws Exception {
            UserUpdateRequestDTO userCreate = new UserUpdateRequestDTO(
                    "notemail.com",
                    "Victor",
                    "Reis"
            );

            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(userCreate)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenFirstNameNull() throws Exception {
            UserUpdateRequestDTO userCreate = new UserUpdateRequestDTO(
                    "notemail.com",
                    null,
                    "Reis"
            );

            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(userCreate)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenFirstNameEmpty() throws Exception {
            UserUpdateRequestDTO userCreate = new UserUpdateRequestDTO(
                    "notemail.com",
                    "",
                    "Reis"
            );

            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(userCreate)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenFirstNameBlank() throws Exception {
            UserUpdateRequestDTO userCreate = new UserUpdateRequestDTO(
                    "notemail.com",
                    " ",
                    "Reis"
            );

            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(userCreate)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenLastNameNull() throws Exception {
            UserUpdateRequestDTO userCreate = new UserUpdateRequestDTO(
                    "notemail.com",
                    "Victor",
                    null
            );

            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(userCreate)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenLastNameEmpty() throws Exception {
            UserUpdateRequestDTO userCreate = new UserUpdateRequestDTO(
                    "notemail.com",
                    "Victor",
                    ""
            );

            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(userCreate)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenLastNameBlank() throws Exception {
            UserUpdateRequestDTO userCreate = new UserUpdateRequestDTO(
                    "notemail.com",
                    "Victor",
                    " "
            );

            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(userCreate)))
                    .andExpect(status().isBadRequest());
        }

    }

    @Nested
    class UpdateUserRequestDTOTest {

        @Test
        void shouldReturnBadRequestWhenEmailIsBlank() throws Exception {
            UserUpdateRequestDTO userCreate = new UserUpdateRequestDTO(
                    "",
                    "Victor",
                    "Reis"
            );

            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(userCreate)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenEmailIsNotEmail() throws Exception {
            UserUpdateRequestDTO userCreate = new UserUpdateRequestDTO(
                    "notemail.com",
                    "Victor",
                    "Reis"
            );

            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(userCreate)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenFirstNameNull() throws Exception {
            UserUpdateRequestDTO userCreate = new UserUpdateRequestDTO(
                    "notemail.com",
                    null,
                    "Reis"
            );

            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(userCreate)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenFirstNameEmpty() throws Exception {
            UserUpdateRequestDTO userCreate = new UserUpdateRequestDTO(
                    "notemail.com",
                    "",
                    "Reis"
            );

            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(userCreate)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenFirstNameBlank() throws Exception {
            UserUpdateRequestDTO userCreate = new UserUpdateRequestDTO(
                    "notemail.com",
                    " ",
                    "Reis"
            );

            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(userCreate)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenLastNameNull() throws Exception {
            UserUpdateRequestDTO userCreate = new UserUpdateRequestDTO(
                    "notemail.com",
                    "Victor",
                    null
            );

            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(userCreate)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenLastNameEmpty() throws Exception {
            UserUpdateRequestDTO userCreate = new UserUpdateRequestDTO(
                    "notemail.com",
                    "Victor",
                    ""
            );

            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(userCreate)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenLastNameBlank() throws Exception {
            UserUpdateRequestDTO userCreate = new UserUpdateRequestDTO(
                    "notemail.com",
                    "Victor",
                    " "
            );

            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(userCreate)))
                    .andExpect(status().isBadRequest());
        }

    }

}