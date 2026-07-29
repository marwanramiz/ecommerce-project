package com.marwan.repository;
import com.marwan.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderService extends MongoRepository<Order,String>{
}