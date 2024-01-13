package com.example.oxygen.pojo;

import com.example.oxygen.entity.Item;
import com.example.oxygen.entity.ItemHistory;
import com.example.oxygen.entity.Order;
import com.example.oxygen.repository.ItemHistoryRepository;
import com.example.oxygen.service.ItemHistoryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Getter
@Setter
public class OrderPojo {
    private long id;
    private Date orderDate;
    private boolean isPaid;
    private List<ItemPojo> items;
    private double totalPrice;

    public static OrderPojo fromEntity(Order order) {
        OrderPojo pojo = new OrderPojo();
        pojo.setId(order.getId());
        pojo.setOrderDate(order.getOrderDate());
        pojo.setPaid(order.isPaid());
        pojo.setTotalPrice(order.getTotalPrice());
        return pojo;
    }

    public static Order toEntity(OrderPojo pojo) {
        Order order = new Order();
        order.setPaid(pojo.isPaid());
        order.setId(pojo.getId());
        order.setOrderDate(pojo.getOrderDate());
        order.setTotalPrice(pojo.getTotalPrice());
        List<Item> items = new ArrayList<>();
        order.setItems(items);
        for (ItemPojo item : pojo.getItems()) {
            items.add(ItemPojo.toEntity(item));
        }
        return order;
    }

}
