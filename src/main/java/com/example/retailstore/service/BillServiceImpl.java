package com.example.retailstore.service;

import com.example.retailstore.dao.BillRepository;
import com.example.retailstore.dao.UserRepository;
import com.example.retailstore.model.Bill;
import com.example.retailstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService{

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiscountCalculatorService discountCalculatorService;

    @Override
    public Bill submit(Bill bill) {

        // Find user by ID

        Optional<User> user = userRepository.findById(bill.getUserId());

        if (user.isEmpty())
            throw new IllegalArgumentException("Invalid User ID");

        // Calculate Total Amount

        bill.setTotalAmount(bill.getItems().stream().mapToDouble(i-> i.getQuantity() * i.getPrice()).sum());
        bill.setTotalAmountNoGrocery(bill.getItems().stream().filter(i-> !i.isGrocery()).mapToDouble(i-> i.getQuantity() * i.getPrice()).sum());

        // calculate discount
        Bill finalBill = discountCalculatorService.calculateDiscount(bill, user.get());

        // save bill
        return billRepository.save(finalBill);


    }

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }
}
