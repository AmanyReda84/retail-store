package com.example.retailstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "bills")
public class Bill {
    @Id
    private String id;
    private String code;
    private String userId;
    private Date date;
    private List<Item> items;

    // % discount
    private double percentageDiscount;
    private double percentageDiscountValue;

    // flat rate discount
    private double flatRateDiscountValue;

    private double totalDiscount;

    // totals
    private double totalAmount;
    private double totalAmountNonGrocery;
    private double totalAmountAfterDiscount;


}
