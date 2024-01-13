package com.example.oxygen.controller;

import com.example.oxygen.pojo.ItemPojo;
import com.example.oxygen.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items")
    public List<ItemPojo> findAll() {
        return itemService.findAllItems(); }

    @PostMapping("/items/search")
    public List<ItemPojo> findByName(@RequestBody ItemSearchRequest request) {
        return itemService.findByName(request.getName());
    }

    @PostMapping("/admin/items/new")
    public ItemPojo createItem(@RequestBody ItemPojo pojo) {
        return itemService.saveItem(pojo);
    }

    @PutMapping("/admin/items/update")
    public ItemPojo updateItem(@RequestBody ItemPojo pojo) {
        return itemService.saveItem(pojo);
    }
}
