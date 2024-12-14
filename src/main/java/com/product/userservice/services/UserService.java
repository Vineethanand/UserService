package com.product.userservice.services;

import com.product.userservice.models.Token;
import com.product.userservice.models.User;

public interface UserService {
    public User findUserById(int id);

    User signUp(String name, String email, String password);

    Token login(String email, String password);

    User validateToken(String token);
}
