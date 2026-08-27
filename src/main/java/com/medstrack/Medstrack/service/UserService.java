package com.medstrack.Medstrack.service;

import com.medstrack.Medstrack.dto.RegisterUserDTO;
import com.medstrack.Medstrack.exception.EmailAlreadyExistsException;
import com.medstrack.Medstrack.model.User;
import com.medstrack.Medstrack.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegisterUserDTO dto) {

        // 1. Check if the email is already registered
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email is already registered");
        }

        // 2. Map DTO to entity
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());

        // 3. Encode password
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // 4. Persist user
        userRepository.save(user);
    }
}