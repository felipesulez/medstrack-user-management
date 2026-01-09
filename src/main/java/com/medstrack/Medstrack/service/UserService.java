package com.medstrack.Medstrack.service;

import com.medstrack.Medstrack.dto.RegisterUserDTO;
import com.medstrack.Medstrack.exception.EmailAlreadyExistsException;
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

    public void registrarUsuario(RegisterUserDTO dto) {

        // 1. Validar si el correo ya existe (ERROR DE NEGOCIO)
        if (repository.findByCorreo(dto.getCorreo()).isPresent()) {
            throw new EmailAlreadyExistsException("El correo ya está registrado");
        }

        // 2. Mapear DTO → Entidad
        User user = new User();
        user.setCorreo(dto.getCorreo());
        user.setNombre(dto.getNombre());

        // 3. Encriptar password
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // 4. Guardar
        repository.save(user);
    }
}
