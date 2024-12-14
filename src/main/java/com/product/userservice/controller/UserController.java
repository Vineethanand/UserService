package com.product.userservice.controller;

import com.product.userservice.dtos.LoginRequestDto;
import com.product.userservice.dtos.SignUpRequestDto;
import com.product.userservice.dtos.UserResponseDto;
import com.product.userservice.models.Token;
import com.product.userservice.models.User;
import com.product.userservice.services.UserService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        User user = userService.signUp(signUpRequestDto.getName(), signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
        return UserResponseDto.from(user);
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto loginRequestDto)
    {
        return userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }

    @GetMapping("/validate/{token}")
    public UserResponseDto validateToken(@PathVariable String token)
    {
        User user = userService.validateToken(token);
        return UserResponseDto.from(user);
    }
}

