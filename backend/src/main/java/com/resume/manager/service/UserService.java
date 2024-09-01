package com.resume.manager.service;

import com.resume.manager.constant.RoleEnum;
import com.resume.manager.entity.UserEntity;
import com.resume.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity register(String username, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setRole(RoleEnum.USER);
        return userRepository.save(userEntity);
    }

    public UserEntity changePassword(String username, String newPassword) {
        UserEntity userEntity = userRepository.findByUsername(username);
        userEntity.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(userEntity);
    }
}
