package com.example.oxygen.service;

import com.example.oxygen.entity.Item;
import com.example.oxygen.pojo.ItemPojo;
import com.example.oxygen.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemHistoryService itemHistoryService;
    public ItemPojo saveItem(ItemPojo pojo) {
        Item item = ItemPojo.toEntity(pojo);
        ItemPojo itemPojo = ItemPojo.fromEntity(itemRepository.save(item));
        item.setId(itemPojo.getId());
        itemHistoryService.save(item);
        return itemPojo;
    }

    public List<ItemPojo> findAllItems() {
        List<ItemPojo> result = new ArrayList<>();
        for (Item item : itemRepository.findAll()) {
            result.add(ItemPojo.fromEntity(item));
        }
        return result;
    }

    public List<ItemPojo> findByName(String name) {
        List<ItemPojo> result = new ArrayList<>();
        for (Item item : itemRepository.findAllByNameContainingIgnoreCase(name)) {
            result.add(ItemPojo.fromEntity(item));
        }
        return result;
    }
}
