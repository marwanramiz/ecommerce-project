package com.marwan.controller;

import com.marwan.model.Product;
import com.marwan.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @GetMapping
    public List<Product> getAll(){
        return productService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable String id){
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Product create(@RequestBody Product product){
        return productService.save(product);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable String id,@RequestBody Product product){
        return productService.findById(id)
                .map(existing->{
                    product.setId(id);
                    return ResponseEntity.ok(productService.save(product));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return productService.findById(id)
                .map(existing -> {
                    productService.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.status(404).build());
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category){
        return productService.findByCategory(category);
    }
}