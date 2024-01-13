package com.example.oxygen.pojo;

import com.example.oxygen.entity.Order;
import com.example.oxygen.entity.Package;
import com.example.oxygen.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class PackagePojo {
    private long id;
    private List<OrderPojo> orders;
    private Date shipmentDate;
    private Date deliveryDate;

    public static PackagePojo fromEntity(Package p) {
        PackagePojo pojo = new PackagePojo();
        pojo.setId(p.getId());
        pojo.setDeliveryDate(p.getDeliveryDate());
        pojo.setShipmentDate(p.getShipmentDate());
        return pojo;
    }

    public static Package toEntity(PackagePojo pojo) {
        Package p = new Package();
        p.setId(pojo.getId());
        p.setDeliveryDate(pojo.getDeliveryDate());
        p.setShipmentDate(pojo.getShipmentDate());
        List<Order> orders = new ArrayList<>();
        p.setOrders(orders);
        for (OrderPojo orderPojo : pojo.getOrders()) {
            orders.add(OrderPojo.toEntity(orderPojo));
        }
        return p;
    }
}
