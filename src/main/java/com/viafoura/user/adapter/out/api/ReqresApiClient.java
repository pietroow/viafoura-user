package com.viafoura.user.adapter.out.api;

import com.viafoura.user.adapter.out.api.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ReqresApiClient {

    private final WebClient webClient;

    @Autowired
    public ReqresApiClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://reqres.in/api")
                .build();
    }

    public String fetchAvatarUrl(int userId) {
        return webClient.get()
                .uri("/users/{id}", userId)
                .retrieve()
                .bodyToMono(UserDTO.class)
                .map(map -> map.data().avatar())
                .onErrorReturn("default-avatar.jpg")
                .block();
    }

}