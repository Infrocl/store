package com.example.oxygen.pojo;

import com.example.oxygen.entity.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPojo {
    private long id;
    private String name;
    private double price;

    public static ItemPojo fromEntity(Item item) {
        ItemPojo pojo = new ItemPojo();
        pojo.setId(item.getId());
        pojo.setName(item.getName());
        pojo.setPrice(item.getPrice());
        return pojo;
    }

    public static Item toEntity(ItemPojo pojo) {
        Item item = new Item();
        item.setId(pojo.getId());
        item.setName(pojo.getName());
        item.setPrice(pojo.getPrice());
        return item;
    }
}
