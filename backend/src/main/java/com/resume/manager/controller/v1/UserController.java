package com.resume.manager.controller.v1;

import com.resume.manager.entity.ResumeEntity;
import com.resume.manager.entity.UserEntity;
import com.resume.manager.exception.ResumeNotFoundException;
import com.resume.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}/password")
    public ResponseEntity<UserEntity> changePassword(Principal principal, @RequestParam String newPassword) {
        try {
            UserEntity updatedUser = userService.changePassword(getUserId(principal.getName()), newPassword);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/resumes")
    public ResponseEntity<List<ResumeEntity>> getResumes(Principal principal) {
        try {
            List<ResumeEntity> resumeEntityList = userService.getResumes(getUserId(principal.getName()));
            return ResponseEntity.ok(resumeEntityList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}/defaultResume")
    public ResponseEntity setDefaultResume(Principal principal,Long resumeId) {
        try {
            userService.setDefaultResume(getUserId(principal.getName()),resumeId);
            return ResponseEntity.ok().build();
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
