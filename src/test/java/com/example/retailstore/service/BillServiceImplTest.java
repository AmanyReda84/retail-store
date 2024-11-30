package com.example.retailstore.service;

import com.example.retailstore.dao.BillRepository;
import com.example.retailstore.dao.UserRepository;
import com.example.retailstore.model.Bill;
import com.example.retailstore.model.Item;
import com.example.retailstore.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BillServiceImplTest {

    @Mock
    private BillRepository billRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DiscountCalculatorService discountCalculatorService;

    @InjectMocks
    private BillServiceImpl billService;

    private Bill bill;
    private User user;

    @BeforeEach
    void setUp() {
        bill = new Bill();
        bill.setUserId("user1");
        bill.setItems(List.of(new Item("i1", 50.0,true),new Item("i2",100,false)));

        user = new User();
        user.setId("user1");

    }

    @Test
    void testSubmit_ValidUser() {
        when(userRepository.findById(bill.getUserId())).thenReturn(Optional.of(user));
        when(discountCalculatorService.calculateDiscount(any(Bill.class), any(User.class))).thenReturn(bill);
        when(billRepository.save(any(Bill.class))).thenReturn(bill);

        Bill result = billService.submit(bill);

        assertNotNull(result);
        verify(userRepository, times(1)).findById(bill.getUserId());
        verify(discountCalculatorService, times(1)).calculateDiscount(any(Bill.class), any(User.class));
        verify(billRepository, times(1)).save(any(Bill.class));
    }

    @Test
    void testSubmit_InvalidUser() {
        when(userRepository.findById(bill.getUserId())).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            billService.submit(bill);
        });

        assertEquals("Invalid User ID", exception.getMessage());
        verify(userRepository, times(1)).findById(bill.getUserId());
        verify(discountCalculatorService, times(0)).calculateDiscount(any(Bill.class), any(User.class));
        verify(billRepository, times(0)).save(any(Bill.class));
    }

    @Test
    void testSubmit_TotalAmount(){
        when(userRepository.findById(bill.getUserId())).thenReturn(Optional.of(user));
        when(discountCalculatorService.calculateDiscount(any(Bill.class), any(User.class))).thenReturn(bill);
        when(billRepository.save(any(Bill.class))).thenReturn(bill);

        Bill result = billService.submit(bill);

        assertNotNull(result);
        verify(userRepository, times(1)).findById(bill.getUserId());
        assertEquals(100,result.getTotalAmountNonGrocery());
        assertEquals(150,result.getTotalAmount());
        verify(discountCalculatorService, times(1)).calculateDiscount(any(Bill.class), any(User.class));
        verify(billRepository, times(1)).save(any(Bill.class));
    }

    @Test
    void testFindAll() {
        when(billRepository.findAll()).thenReturn(List.of(bill));
        List<Bill>bills = billService.findAll();
        verify(billRepository, times(1)).findAll();
        assertEquals(1, bills.size());
    }
}

