package com.product.userservice.services;

import com.product.userservice.models.Token;
import com.product.userservice.models.User;
import com.product.userservice.repos.Tokenrepo;
import com.product.userservice.repos.UserRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EcommerceUserService implements UserService {

    private UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Tokenrepo tokenrepo;

    public EcommerceUserService(UserRepo userRepo,
                                BCryptPasswordEncoder bCryptPasswordEncoder,
                                Tokenrepo tokenrepo) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenrepo = tokenrepo;
    }

    @Override
    public User findUserById(int id) {
        return null;
    }

    @Override
    public User signUp(String name, String email, String password) {
        User newuser = new User();
        newuser.setName(name);
        newuser.setEmail(email);
        newuser.setHashedPassword(bCryptPasswordEncoder.encode(password));
        return userRepo.save(newuser);
    }

    @Override
    public Token login(String email, String password) {
        Optional<User> optional_user = userRepo.findByEmail(email);
        if(optional_user.isEmpty()) {
            throw new UsernameNotFoundException("User with " + email + " not found");
        }
        User user = optional_user.get();
        if(!bCryptPasswordEncoder.matches(password, user.getHashedPassword())) {
            throw new BadCredentialsException("Wrong password");
        }

        Token token = generateToken(user);
        return tokenrepo.save(token);
    }

    @Override
    public User validateToken(String token) {
//        A token is valid if
//            1. Token exists in DB
//            2. Token is not expired
//            3. Token is not marked as deleted
        Optional<Token> optional_token = tokenrepo
                .findByValueAndDeletedAndExpiryAtGreaterThan(token, false, System.currentTimeMillis());

        if(optional_token.isEmpty()) {
            return null;
        }
        User user = optional_token.get().getUser();
        return user;
    }

    private Token generateToken(User user) {
        Token token = new Token();
        token.setValue(RandomStringUtils.randomAlphanumeric(10));
        token.setExpiryAt(System.currentTimeMillis() + 1000 * 60 * 60 * 24);
        token.setUser(user);
        return token;
    }
}
