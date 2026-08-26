package com.medstrack.Medstrack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medstrack.Medstrack.dto.RegisterUserDTO;
import com.medstrack.Medstrack.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deberiaRegistrarUsuarioCorrectamente() throws Exception {

        // 1. Datos de entrada (JSON)
        String requestJson = """
        {
          "correo": "felipe@medstrack.com",
          "nombre": "Felipe",
          "password": "Medstrack1"
        }
        """;

        // 2. Ejecución y verificación
        mockMvc.perform(
                        post("/api/usuarios/registro")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                )
                .andExpect(status().isCreated());

        // 3. Verifica que el controller llamó al servicio
        verify(userService).registrarUsuario(any(RegisterUserDTO.class));
    }
}