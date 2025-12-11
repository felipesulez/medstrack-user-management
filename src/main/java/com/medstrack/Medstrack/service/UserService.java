package com.medstrack.Medstrack.service;

import com.medstrack.Medstrack.dto.RegisterUserDTO;
import com.medstrack.Medstrack.model.User;
import com.medstrack.Medstrack.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registrarUsuario(RegisterUserDTO dto) throws Exception {

        // 1. Validar si el correo ya existe
        if (repository.findByCorreo(dto.getCorreo()).isPresent()) {
            throw new Exception("El correo ya está registrado");
        }

        // 2. Mapear DTO → Entidad User
        User user = new User();
        user.setCorreo(dto.getCorreo());
        user.setNombre(dto.getNombre());

        // 3. Encriptar password
        String passwordEncriptada = passwordEncoder.encode(dto.getPassword());
        user.setPassword(passwordEncriptada);

        // 4. Guardar en DB
        repository.save(user);
    }
}
