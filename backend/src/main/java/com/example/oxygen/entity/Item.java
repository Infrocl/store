package com.example.oxygen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "items", schema = "public")
@Setter
@Getter
@RequiredArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
    private List<Order> orders;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private double price;
}