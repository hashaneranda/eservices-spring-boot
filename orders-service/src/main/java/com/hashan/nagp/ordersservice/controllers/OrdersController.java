package com.hashan.nagp.ordersservice.controllers;

import com.hashan.nagp.ordersservice.models.OrderModel;
import com.hashan.nagp.ordersservice.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class OrdersController {
    @Autowired
    private OrdersService ordersService;


    @GetMapping("/orders")
    public List<OrderModel> getAllOrders() {
        return ordersService.getAllOrders();
    }


    @GetMapping("/orders/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable("id") String id) {
        OrderModel order = ordersService.getOrderById(id);

        if (order != null) {
            return ResponseEntity.ok().body(order);
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping("/orders")
    public ResponseEntity<Object> addOrder(@RequestBody OrderModel orderModel) {
        OrderModel order = ordersService.getOrderById(orderModel.getId());

        if (order != null) {
            return ResponseEntity.status(403).body("Order already exists");
        }

        OrderModel orderModelResult = ordersService.addOrder(orderModel);
        return ResponseEntity.ok().body(orderModelResult);
    }


    @PutMapping("/orders")
    public ResponseEntity<Object> updateService(@RequestBody OrderModel orderModel) {
        OrderModel orderModelResult = ordersService.updateOrder(orderModel);
        return ResponseEntity.ok().body(orderModelResult);
    }


    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable("id") String id) {
        OrderModel order = ordersService.getOrderById(id);

        if (order != null) {
            ordersService.deleteOrder(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
