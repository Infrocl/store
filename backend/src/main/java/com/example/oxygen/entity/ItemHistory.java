package com.example.oxygen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "item_history", schema = "public")
@NoArgsConstructor
@Setter
@Getter
public class ItemHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itemId;

    private String name;

    private double price;

    @Column(columnDefinition = "TIMESTAMP")
    private Date auditTimestamp;
    public ItemHistory(long itemId, String name, double price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.auditTimestamp = new Date();
    }
}
