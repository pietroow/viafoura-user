package com.viafoura.user.port.in.web;

import com.viafoura.user.adapter.out.api.dto.UserData;
import com.viafoura.user.port.in.web.dto.UserCreateDTO;
import com.viafoura.user.domain.User;
import com.viafoura.user.port.in.ManageUserUseCase;
import com.viafoura.user.port.in.web.dto.UserUpdateRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final ManageUserUseCase manageUserUseCase;

    @Operation(summary = "Create a new user")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    @PostMapping
    public ResponseEntity<User>  createUser(@Valid @RequestBody UserCreateDTO userCreate) {
        User userCreated = manageUserUseCase.createUser(userCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location)
                .build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Long id,
                           @Validated @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        manageUserUseCase.updateUser(id, userUpdateRequestDTO);
    }

    @GetMapping
    public List<UserData> listUsers() {
        return manageUserUseCase.listUsers();
    }

    @GetMapping("/{id}")
    public UserData getUser(@PathVariable Long id) {
        return manageUserUseCase.findById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        manageUserUseCase.deleteUser(id);
    }

}