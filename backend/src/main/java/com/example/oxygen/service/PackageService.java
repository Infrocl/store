package com.example.oxygen.service;

import com.example.oxygen.entity.Item;
import com.example.oxygen.entity.Order;
import com.example.oxygen.entity.Package;
import com.example.oxygen.entity.User;
import com.example.oxygen.pojo.ItemPojo;
import com.example.oxygen.pojo.OrderPojo;
import com.example.oxygen.pojo.PackagePojo;
import com.example.oxygen.pojo.UserPojo;
import com.example.oxygen.repository.OrderRepository;
import com.example.oxygen.repository.PackageRepository;
import com.example.oxygen.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class PackageService {
    private final PackageRepository packageRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ItemHistoryService itemHistoryService;

    public List<PackagePojo> findAllPackagesByUser(long userId) {
        List<PackagePojo> packagePojos = new LinkedList<>();
        for (Package p : packageRepository.findAllByUserId(userId)) {
            PackagePojo packagePojo = PackagePojo.fromEntity(p);
            List<OrderPojo> orderPojos = new LinkedList<>();
            for (Order order: p.getOrders()) {
                List<ItemPojo> items = new LinkedList<>();
                OrderPojo orderPojo = OrderPojo.fromEntity(order);
                for (Item item : order.getItems()) {
                    items.add(itemHistoryService.getCorrectItem(item.getId(), order.getOrderDate()));
                }
                orderPojo.setItems(items);
                orderPojos.add(orderPojo);
            }
            packagePojo.setOrders(orderPojos);
            packagePojos.add(packagePojo);
        }
        return packagePojos;
    }

    public PackagePojo createPackage(long userId, List<Long> orderIds, Date shipmentDate, Date deliveryDate) {
        Package p = new Package();
        List<Order> orders = new LinkedList<>();
        for (long index : orderIds) {
            orders.add(orderRepository.findById(index).orElseThrow());
        }
        p.setOrders(orders);
        p.setDeliveryDate(deliveryDate);
        p.setShipmentDate(shipmentDate);
        p.setUser(userRepository.findById(userId).orElseThrow());
        return PackagePojo.fromEntity(packageRepository.save(p));
    }
}
