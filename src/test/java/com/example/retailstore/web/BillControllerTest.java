package com.example.retailstore.web;

import com.example.retailstore.model.Bill;
import com.example.retailstore.model.Item;
import com.example.retailstore.service.BillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BillControllerTest {

    @Mock
    private BillService billService;

    @InjectMocks
    private BillController billController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSubmitBill() {
        Bill bill = new Bill();
        bill.setId("bill1");
        bill.setCode("bill1");
        bill.setItems(List.of(new Item("item1", 50.0,true)));

        when(billService.submit(any(Bill.class))).thenReturn(bill);

        ResponseEntity<Bill> response = billController.submitBill(bill);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(bill, response.getBody());
        verify(billService, times(1)).submit(bill);
    }



    @Test
    public void testGetBills() {
        Bill bill1 = new Bill();
        bill1.setId("bill1");
        bill1.setTotalAmount(100.0);

        Bill bill2 = new Bill();
        bill2.setId("bill2");
        bill2.setTotalAmount(200.0);

        List<Bill> bills = Arrays.asList(bill1, bill2);

        when(billService.findAll()).thenReturn(bills);

        ResponseEntity<List<Bill>> response = billController.getBills();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bills, response.getBody());
        verify(billService, times(1)).findAll();
    }
}

