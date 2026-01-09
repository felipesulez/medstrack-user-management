package com.medstrack.Medstrack.controller;

import com.medstrack.Medstrack.dto.RegisterUserDTO;
import com.medstrack.Medstrack.response.ApiResponse;
import com.medstrack.Medstrack.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/registro")
    public ResponseEntity<ApiResponse<Void>> registrar(
            @RequestBody @Valid RegisterUserDTO dto) throws Exception {

        service.registrarUsuario(dto);

        return ResponseEntity.ok(
                ApiResponse.success("Usuario registrado correctamente")
        );
    }
}
