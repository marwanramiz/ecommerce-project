package com.marwan.repository;

import com.marwan.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface CartRepository extends MongoRepository<Cart,String>{
}