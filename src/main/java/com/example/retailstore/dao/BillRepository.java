package com.example.retailstore.dao;

import com.example.retailstore.model.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BillRepository extends MongoRepository<Bill,String> {
}
