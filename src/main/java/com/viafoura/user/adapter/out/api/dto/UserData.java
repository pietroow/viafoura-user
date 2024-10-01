package com.viafoura.user.adapter.out.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserData(
        @JsonProperty("id") int id,
        @JsonProperty("email") String email,
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName,
        @JsonProperty("avatar") String avatar
) {
}