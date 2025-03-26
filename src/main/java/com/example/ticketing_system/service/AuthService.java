package com.example.ticketing_system.service;

import com.example.ticketing_system.model.User;
import com.example.ticketing_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service    // Marks this class as a Spring service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Authenticate user by username and password
    public Optional<User> authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password));
    }

    // Save user to the database
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
