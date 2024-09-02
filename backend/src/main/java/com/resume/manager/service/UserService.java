package com.resume.manager.service;

import com.resume.manager.constant.RoleEnum;
import com.resume.manager.entity.ResumeEntity;
import com.resume.manager.entity.UserEntity;
import com.resume.manager.exception.ResumeNotFoundException;
import com.resume.manager.repository.ResumeRepository;
import com.resume.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity register(String username, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setRole(RoleEnum.USER);
        return userRepository.save(userEntity);
    }

    public Long getUserId(String username){
        return userRepository.findByUsername(username).getId();
    }


    public UserEntity changePassword(Long userId, String newPassword) {
        UserEntity userEntity = userRepository.findById(userId).get();
        userEntity.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(userEntity);
    }

    public List<ResumeEntity> getResumes(Long userId){
        return resumeRepository.findAllByUserId(userId);
    }

    public void setDefaultResume(Long userId,Long resumeId) throws ResumeNotFoundException {
        UserEntity userEntity = userRepository.findById(userId).get();
        boolean isValid = resumeRepository.findAllByUserId(userId).stream().map(ResumeEntity::getId).anyMatch(e->e.equals(resumeId));
        if(!isValid){
            throw new ResumeNotFoundException("Resume with id = "+resumeId+" not found");
        }
        userEntity.setDefaultResumeId(resumeId);
        userRepository.save(userEntity);
    }
}
