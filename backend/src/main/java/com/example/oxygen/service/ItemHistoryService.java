package com.example.oxygen.service;

import com.example.oxygen.entity.Item;
import com.example.oxygen.entity.ItemHistory;
import com.example.oxygen.pojo.ItemPojo;
import com.example.oxygen.repository.ItemHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class ItemHistoryService {
    private final ItemHistoryRepository itemHistoryRepository;
    public void save(Item item) {
        itemHistoryRepository.save(new ItemHistory(item.getId(), item.getName(), item.getPrice()));
    }

    public ItemPojo getCorrectItem(long itemId, Date orderDate) {
        ItemPojo itemPojo = new ItemPojo();
        itemPojo.setId(itemId);
        ItemHistory itemHistory = itemHistoryRepository.findByIdAndDate(itemId, orderDate).orElseThrow();
        itemPojo.setName(itemHistory.getName());
        itemPojo.setPrice(itemHistory.getPrice());
        return itemPojo;
    }

}
