package com.example.retailstore.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Item {

    private String itemId;
    private String name;
    private int quantity = 1;
    private double price;
    private boolean isGrocery;

    public Item (String itemId , double price , boolean isGrocery){
        this.itemId = itemId;
        this.price = price;
        this.isGrocery = isGrocery;
    }

}
