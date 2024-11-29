package com.example.retailstore.service;

import com.example.retailstore.model.Bill;
import com.example.retailstore.model.User;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountCalculatorServiceImpl implements DiscountCalculatorService{

    @Autowired
    private KieContainer kieContainer;

    @Override
    public Bill calculateDiscount(Bill bill, User user) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("finalBill", bill);
        kieSession.insert(user);
        kieSession.insert(bill);
        kieSession.fireAllRules();
        kieSession.dispose();
        return bill;
    }
}
