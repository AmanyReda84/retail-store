package com.example.retailstore.service;


import com.example.retailstore.model.Bill;
import com.example.retailstore.model.Item;
import com.example.retailstore.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DiscountCalculatorServiceImplTest {

    @Autowired
    private DiscountCalculatorServiceImpl discountCalculatorService;

    @Test
    public void testCalculateDiscount_employee_30() {

        User user = new User();
        user.setId("1");
        user.setType("employee");
        user.setDate(new Date());

        Bill bill = new Bill();
        bill.setUserId("1");
        bill.setItems(List.of(new Item("1",50,true),new Item("2",50,false)));
        bill.setTotalAmountNonGrocery(bill.getItems().stream().filter(i-> !i.isGrocery()).mapToDouble(i-> i.getQuantity() * i.getPrice()).sum());
        bill.setTotalAmount(bill.getItems().stream().mapToDouble(i-> i.getQuantity() * i.getPrice()).sum());

        Bill result = discountCalculatorService.calculateDiscount(bill, user);
        assertEquals(30, result.getPercentageDiscount());
        assertEquals(15, result.getPercentageDiscountValue());
        assertEquals(0, result.getFlatRateDiscountValue());
        assertEquals(100, result.getTotalAmount());
        assertEquals(85, result.getTotalAmountAfterDiscount());

    }

    @Test
    public void testCalculateDiscount_affiliate_10() {

        User user = new User();
        user.setId("1");
        user.setType("affiliate");
        user.setDate(new Date());

        Bill bill = new Bill();
        bill.setUserId("1");
        bill.setItems(List.of(new Item("1",50,true),new Item("2",50,false)));
        bill.setTotalAmountNonGrocery(bill.getItems().stream().filter(i-> !i.isGrocery()).mapToDouble(i-> i.getQuantity() * i.getPrice()).sum());
        bill.setTotalAmount(bill.getItems().stream().mapToDouble(i-> i.getQuantity() * i.getPrice()).sum());

        Bill result = discountCalculatorService.calculateDiscount(bill, user);
        assertEquals(10, result.getPercentageDiscount());
        assertEquals(5, result.getPercentageDiscountValue());
        assertEquals(0, result.getFlatRateDiscountValue());
        assertEquals(100, result.getTotalAmount());
        assertEquals(95, result.getTotalAmountAfterDiscount());

    }

    @Test
    public void testCalculateDiscount_another_type_5() {

        User user = new User();
        user.setId("1");
        user.setType("another_type");
        user.setDate(Date.from(LocalDate.now().minusYears(2).minusMonths(3).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        Bill bill = new Bill();
        bill.setUserId("1");
        bill.setItems(List.of(new Item("1",50,true),new Item("2",100,false)));
        bill.setTotalAmountNonGrocery(bill.getItems().stream().filter(i-> !i.isGrocery()).mapToDouble(i-> i.getQuantity() * i.getPrice()).sum());
        bill.setTotalAmount(bill.getItems().stream().mapToDouble(i-> i.getQuantity() * i.getPrice()).sum());

        Bill result = discountCalculatorService.calculateDiscount(bill, user);
        assertEquals(0, result.getPercentageDiscount());
        assertEquals(0, result.getPercentageDiscountValue());
        assertEquals(5, result.getFlatRateDiscountValue());
        assertEquals(150, result.getTotalAmount());
        assertEquals(145, result.getTotalAmountAfterDiscount());

    }
}
