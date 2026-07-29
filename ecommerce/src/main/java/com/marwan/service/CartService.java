package com.marwan.service;

import com.marwan.model.Cart;
import com.marwan.repository.CartRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    public CartService(CartRepository cartRepository){
        this.cartRepository=cartRepository;
    }
    public Cart save(Cart cart){
        return cartRepository.save(cart);
    }
    public Optional<Cart> findById(String id){
        return cartRepository.findById(id);
    }
    public void delete(String id){
        cartRepository.deleteById(id);
    }
}