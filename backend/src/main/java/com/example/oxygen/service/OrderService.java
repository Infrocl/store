package com.example.oxygen.service;

import com.example.oxygen.entity.Item;
import com.example.oxygen.entity.Order;
import com.example.oxygen.pojo.ItemPojo;
import com.example.oxygen.pojo.OrderPojo;
import com.example.oxygen.repository.ItemRepository;
import com.example.oxygen.repository.OrderRepository;
import com.example.oxygen.repository.PackageRepository;

import com.example.oxygen.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemHistoryService itemHistoryService;

    public List<OrderPojo> findAllOrdersByUser(long userId) {
        List<OrderPojo> result = new ArrayList<>();
        for (Order order : orderRepository.findAllByUserId(userId)) {
            OrderPojo pojo = OrderPojo.fromEntity(order);
            List<ItemPojo> items = new LinkedList<>();
            for (Item item : order.getItems()) {
                items.add(itemHistoryService.getCorrectItem(item.getId(), pojo.getOrderDate()));
            }
            pojo.setItems(items);
            result.add(pojo);
        }
        return result;
    }

    public OrderPojo createOrder(long userId, OrderPojo pojo) {
        Order order = OrderPojo.toEntity(pojo);
        order.setUser(userRepository.findById(userId).orElseThrow());
        double price = 0;
        for (ItemPojo item : pojo.getItems()) {
            price+= item.getPrice();
        }
        order.setTotalPrice(price);
        return OrderPojo.fromEntity(orderRepository.save(order));
    }
}
