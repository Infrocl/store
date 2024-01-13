package com.example.oxygen.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageRequest {
    private List<Long> orders;
    private Date shipmentDate;
    private Date deliveryDate;
}
