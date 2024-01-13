package com.example.oxygen.pojo;

import com.example.oxygen.entity.Order;
import com.example.oxygen.entity.Package;
import com.example.oxygen.entity.Role;
import com.example.oxygen.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserPojo {
    private long id;
    private String name;
    private String email;
    private Role role;
    private Date registrationDate;
    private Date birthDate;
    private List<OrderPojo> orders;
    private List<PackagePojo> packages;

    public static UserPojo fromEntity(User user) {
        UserPojo pojo = new UserPojo();
        pojo.setId(user.getId());
        pojo.setName(user.getName());
        pojo.setEmail(user.getEmail());
        pojo.setRole(user.getRole());
        pojo.setBirthDate(user.getBirthDate());
        pojo.setRegistrationDate(user.getRegistrationDate());
        return pojo;
    }

    public static User toEntity(UserPojo pojo) {
        User user = new User();
        user.setId(pojo.getId());
        user.setName(pojo.getName());
        user.setBirthDate(pojo.getBirthDate());
        user.setRegistrationDate(pojo.getRegistrationDate());
        user.setEmail(pojo.getEmail());
        user.setRole(pojo.getRole());
        return user;
    }
}
