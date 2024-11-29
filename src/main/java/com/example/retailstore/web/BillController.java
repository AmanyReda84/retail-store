package com.example.retailstore.web;

import com.example.retailstore.model.Bill;
import com.example.retailstore.service.BillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/bill")
@Tag(name = "Bill API", description = "Bill API")
public class BillController {

    @Autowired
    private BillService billService;


    @PostMapping
    @Operation(summary = "Submit Bill" , description = "Submit New Bill")
    public ResponseEntity<Bill> addUser(@RequestBody Bill bill) {
        Bill updatedBill = billService.submit(bill);
        return new ResponseEntity<>(updatedBill, HttpStatus.CREATED);
    }


}

