package com.example.retailstore.web;

import com.example.retailstore.model.Bill;
import com.example.retailstore.service.BillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/bill")
@Tag(name = "Bill API", description = "Bill API")
public class BillController {

    @Autowired
    private BillService billService;


    @PostMapping
    @Operation(summary = "Submit Bill" , description = "Submit New Bill")
    public ResponseEntity<Bill> submitBill(@RequestBody Bill bill) {
        Bill updatedBill = billService.submit(bill);
        return new ResponseEntity<>(updatedBill, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "List Bills" , description = "Get All Bills")
    public ResponseEntity<List<Bill>> getBills(@RequestBody Bill bill) {
        List<Bill> bills = billService.findAll();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }


}

