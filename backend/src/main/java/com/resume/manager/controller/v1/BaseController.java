package com.resume.manager.controller.v1;

import com.resume.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseController {
    @Autowired
    private UserService userService;

    protected Long getUserId(String username){
        return userService.getUserId(username);
    }
}
