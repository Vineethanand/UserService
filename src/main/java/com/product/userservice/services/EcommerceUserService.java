package com.product.userservice.services;

import com.product.userservice.models.User;
import com.product.userservice.repos.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class EcommerceUserService implements UserService {

    private UserRepo userRepo;

    public EcommerceUserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findUserById(int id) {
        return null;
    }
}
