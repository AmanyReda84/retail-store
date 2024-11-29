package com.example.retailstore.service;

import com.example.retailstore.model.Bill;
import com.example.retailstore.model.User;

public interface DiscountCalculatorService {

    Bill calculateDiscount(Bill bill , User user);
}
