package com.marwan.controller;

import com.marwan.model.Cart;
import com.marwan.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    public CartController(CartService cartService){
        this.cartService=cartService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getById(@PathVariable String id){
        return cartService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Cart create(@RequestBody Cart cart){
        return cartService.save(cart);
    }
    @DeleteMapping
    public ResponseEntity<Object> delete(@PathVariable String id){
        return cartService.findById(id)
                .map(existing->{
                    cartService.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}