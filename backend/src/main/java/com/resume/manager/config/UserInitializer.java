package com.resume.manager.config;

import com.resume.manager.constant.RoleEnum;
import com.resume.manager.entity.UserEntity;
import com.resume.manager.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        String username = "admin"; // Specify your username
        String password = "admin"; // Specify your password

        // Check if the user already exists
        if (userRepository.findByUsername(username) == null) {
            // Create a new user
            UserEntity user = new UserEntity();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password)); // Encrypt the password
            user.setRole(RoleEnum.ADMIN);

            // Save the user to the database
            userRepository.save(user);
            System.out.println("Created new user: " + username);
        } else {
            System.out.println("User already exists: " + username);
        }
    }
}
