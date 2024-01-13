package com.example.oxygen.controller;


import com.example.oxygen.pojo.PackagePojo;
import com.example.oxygen.pojo.UserPojo;
import com.example.oxygen.service.PackageService;
import com.example.oxygen.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final PackageService packageService;

    @GetMapping("/admin/users")
    public List<UserPojo> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/shop/{userId}")
    public UserPojo findUserById(@PathVariable long userId) {
        return  userService.findUserById(userId);
    }

    @GetMapping("/shop/{userId}/packages")
    public List<PackagePojo> findPackagesByUserId(@PathVariable long userId) {
        return packageService.findAllPackagesByUser(userId);
    }
//    @GetMapping("/packages")
//    public List<PackagePojo> findAllPackages() {
//        return packageService.findAll();
//    }

    @PostMapping("/admin/{userId}/packages/new")
    public PackagePojo createPackage (@PathVariable long userId, @RequestBody PackageRequest request) {
        return packageService.createPackage(userId, request.getOrders(), request.getShipmentDate(), request.getDeliveryDate());
    }

}
