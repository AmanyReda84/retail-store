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
    private double totalAmount;
    private double totalAmountNoGrocery;
    private double percentageDiscount;
    private double flatRateDiscountValue;
    private double percentageDiscountValue;
    private double totalDiscount;
    private double totalAfterDiscount;
    private List<Item> items;

}
