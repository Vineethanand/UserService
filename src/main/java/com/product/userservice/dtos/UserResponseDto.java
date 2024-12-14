package com.product.userservice.dtos;

import com.product.userservice.models.Role;
import com.product.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private String name;
    private String email;
    /*
    We can have a RoleDto as well here instead of role
    If we don't want to share some details to the client
    Here only role name is there so that we are using
    Role directly.
     */
    private List<Role> roleList;


    public static  UserResponseDto from(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setRoleList(user.getRoles());
        return userResponseDto;
    }
}
