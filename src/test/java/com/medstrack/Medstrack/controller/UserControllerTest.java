package com.medstrack.Medstrack.controller;

import com.medstrack.Medstrack.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false) // 🛡️ Desactiva la seguridad para este test
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

        // 2. Ejecución y Verificación
        mockMvc.perform(
                        post("/api/usuarios/registro")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                )
                .andExpect(status().isOk()); // ✅ Cambiado a isOk porque tu controller devuelve 200
    }
}