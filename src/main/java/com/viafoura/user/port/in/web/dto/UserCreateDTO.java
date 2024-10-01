package com.viafoura.user.port.in.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreateDTO(
        @NotBlank @Email String email,
        @NotBlank String firstName,
        @NotBlank String lastName) {
}


