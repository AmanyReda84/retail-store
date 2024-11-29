package com.example.retailstore.service;

import com.example.retailstore.model.Bill;

import java.util.List;

public interface BillService {
    Bill submit(Bill bill);

    List<Bill> findAll();
}
