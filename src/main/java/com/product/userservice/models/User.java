package com.product.userservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name="users")
public class User extends BaseModel{
    private String email;
    private String hashedPassword;
    private String name;
    private boolean isEmailVerified;
    private List<Role> roles;

}
