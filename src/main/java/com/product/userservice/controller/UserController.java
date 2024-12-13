package com.product.userservice.controller;

import com.product.userservice.dtos.SignUpRequesstDto;
import com.product.userservice.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUp(@RequestBody SignUpRequesstDto signUpRequesstDto)
}

