package com.example.oxygen.service;

import com.example.oxygen.auth.RegisterRequest;
import com.example.oxygen.entity.*;
import com.example.oxygen.entity.Package;
import com.example.oxygen.pojo.ItemPojo;
import com.example.oxygen.pojo.OrderPojo;
import com.example.oxygen.pojo.PackagePojo;
import com.example.oxygen.pojo.UserPojo;
import com.example.oxygen.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ItemHistoryService itemHistoryService;
    public List<UserPojo> findAllUsers() {
        List<UserPojo> users = new LinkedList<>();
        for (User user : userRepository.findAllByRole(Role.USER).orElseThrow()) {
            UserPojo userPojo = UserPojo.fromEntity(user);
            List<OrderPojo> orderPojos = new ArrayList<>();
            for (Order order : user.getOrders()) {
                OrderPojo pojo = OrderPojo.fromEntity(order);
                List<ItemPojo> items = new LinkedList<>();
                for (Item item : order.getItems()) {
                    items.add(itemHistoryService.getCorrectItem(item.getId(), pojo.getOrderDate()));
                }
                pojo.setItems(items);
                orderPojos.add(pojo);
            }
            userPojo.setOrders(orderPojos);
            List<PackagePojo> packagePojos = new LinkedList<>();
            for (Package p : user.getPackages()) {
                PackagePojo packagePojo = PackagePojo.fromEntity(p);
                List<OrderPojo> orderPojoList = new LinkedList<>();
                for (Order order: p.getOrders()) {
                    List<ItemPojo> items = new LinkedList<>();
                    OrderPojo orderPojo = OrderPojo.fromEntity(order);
                    for (Item item : order.getItems()) {
                        items.add(itemHistoryService.getCorrectItem(item.getId(), order.getOrderDate()));
                    }
                    orderPojo.setItems(items);
                    orderPojoList.add(orderPojo);
                }
                packagePojo.setOrders(orderPojoList);
                packagePojos.add(packagePojo);
            }
            userPojo.setPackages(packagePojos);
            users.add(userPojo);
        }
        return  users;
    }

    public UserPojo findUserById(long userId) {
        return UserPojo.fromEntity(userRepository.findById(userId).orElseThrow());
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User is not found!"));
    }

    public long findUserIdByEmail(String email) {
        return userRepository.findUserIdByEmail(email).orElseThrow();
    }

    public Role findUserRoleByEmail(String email) {
        return userRepository.findUserRoleByEmail(email).orElseThrow();
    }

    public User create(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setBirthDate(request.getBirthDate());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRegistrationDate(new Date());
        user.setRole(Role.USER);
        return userRepository.save(user);
    }
}
