package com.marwan.controller;
import com.marwan.model.Order;
import com.marwan.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }
    @GetMapping
    public List<Order> getAll(){
        return orderService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable String id){
        return orderService.findByid(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Order create(@RequestBody Order order){
        return  orderService.save(order);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable String id,@RequestBody Order order){
        return orderService.findByid(id)
                .map(existing->{
                    order.setId(id);
                    return ResponseEntity.ok(orderService.save(order));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        return orderService.findByid(id)
                .map(existing->{
                    orderService.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}