package com.medstrack.Medstrack.controller;

import com.medstrack.Medstrack.dto.RegisterUserDTO;
import com.medstrack.Medstrack.response.ApiResponse;
import com.medstrack.Medstrack.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(
            @RequestBody @Valid RegisterUserDTO dto) {


        userService.registerUser(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success("User registered successfully")
        );
    }
}
