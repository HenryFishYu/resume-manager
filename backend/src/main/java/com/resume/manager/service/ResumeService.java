package com.resume.manager.service;

import com.resume.manager.entity.ResumeEntity;
import com.resume.manager.entity.UserEntity;
import com.resume.manager.exception.ResumeNotFoundException;
import com.resume.manager.repository.ResumeRepository;
import com.resume.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

@Service
public class ResumeService {

    @Value("${storage.path}")
    private String path;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    public byte[] getResources(String username, String resourceName) throws IOException {
        // Load the HTML file from the resources folder
        ClassPathResource resource = new ClassPathResource(username + "/" + resourceName);

        // Return the content as a ResponseEntity
        return resource.getContentAsByteArray();
    }

    public String getResume(String username) throws IOException, ResumeNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if(Objects.isNull(userEntity.getDefaultResumeId())){
            throw new ResumeNotFoundException("No Resume found for "+userEntity.getUsername());
        }
        ResumeEntity resumeEntity = resumeRepository.findById(userEntity.getDefaultResumeId()).orElse(null);
        if(Objects.isNull(resumeEntity)){
            throw new ResumeNotFoundException("No default Resume found for "+userEntity.getUsername());
        }
        // Load the HTML file from the resources folder
        ClassPathResource html = new ClassPathResource(username + "/" + resumeEntity.getName());
        String resume = StreamUtils.copyToString(html.getInputStream(), StandardCharsets.UTF_8);
        // Return the content as a ResponseEntity
        return resume;
    }
}
