package com.marwan.service;

import com.marwan.model.Product;
import com.marwan.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    public Product save(Product product){
        return productRepository.save(product);
    }
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public Optional<Product> findById(String id){
        return productRepository.findById(id);
    }
    public void delete(String id){
        productRepository.deleteById(id);
    }
    public List<Product> findByCategory(String category){
        return productRepository.findByCategory(category);
    }


}