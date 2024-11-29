package com.example.retailstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SubmitBillResponse {

    private Bill bill;
    private User user;
}
