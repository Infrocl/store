package com.example.oxygen.controller;

import com.example.oxygen.pojo.OrderPojo;
import com.example.oxygen.service.OrderService;
import com.example.oxygen.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;
    private final PackageService packageService;

    @GetMapping("/shop/{userId}/orders")
    public List<OrderPojo> findAllOrdersByUser(@PathVariable long userId) {
        return orderService.findAllOrdersByUser(userId);
    }

    @PostMapping("/shop/{userId}/orders/new")
    public OrderPojo createOrder (@PathVariable long userId, @RequestBody OrderPojo pojo){
        return orderService.createOrder(userId, pojo);
    }

}
