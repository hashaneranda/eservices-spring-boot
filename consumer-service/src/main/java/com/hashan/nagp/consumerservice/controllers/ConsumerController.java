package com.hashan.nagp.consumerservice.controllers;

import com.hashan.nagp.consumerservice.exceptions.OrderPlacementException;
import com.hashan.nagp.consumerservice.models.ConsumerOrderModel;
import com.hashan.nagp.consumerservice.models.ConsumerUserModel;
import com.hashan.nagp.consumerservice.models.OrderModel;
import com.hashan.nagp.consumerservice.services.ConsumerOrderPlacementService;
import com.hashan.nagp.consumerservice.services.ConsumerUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ConsumerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private ConsumerUserService consumerUserService;

    @Autowired
    private ConsumerOrderPlacementService consumerOrderPlacementService;

    @GetMapping("/users")
    public List<ConsumerUserModel> getAllConsumerUsers() {
        return consumerUserService.getAllConsumerUsers();
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getConsumerUser(@PathVariable("id") String id) {
        ConsumerUserModel consumerUser = consumerUserService.getConsumerUserById(id);

        if (consumerUser != null) {
            return ResponseEntity.ok().body(consumerUser);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addConsumerUser(@RequestBody ConsumerUserModel consumerModel) {
        ConsumerUserModel consumerUser = consumerUserService.getConsumerUserById(consumerModel.getId());

        if (consumerUser != null) {
            return ResponseEntity.status(403).body("Consumer already exists");
        }

        ConsumerUserModel consumerUserResult = consumerUserService.addConsumerUser(consumerModel);
        return ResponseEntity.ok().body(consumerUserResult);
    }

    @PutMapping("/users")
    public ResponseEntity<Object> updateConsumerUser(@RequestBody ConsumerUserModel consumerModel) {
        ConsumerUserModel consumerUserResult = consumerUserService.updateConsumerUser(consumerModel);
        return ResponseEntity.ok().body(consumerUserResult);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteConsumerUser(@PathVariable("id") String id) {
        ConsumerUserModel consumerUserModel = consumerUserService.getConsumerUserById(id);

        if (consumerUserModel != null) {
            consumerUserService.deleteConsumerUser(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/users/{id}/orders")
    public ResponseEntity<Object> placeOrder(@PathVariable("id") String id,
                                             @RequestBody ConsumerOrderModel consumerOrderModel) {
        ConsumerUserModel consumerUserModel = consumerUserService.getConsumerUserById(id);

        try {
            if (!consumerOrderPlacementService.validateOrderUtility(consumerOrderModel)) {
                return ResponseEntity.badRequest().body("Utility id is invalid.");
            }
        } catch (OrderPlacementException e) {
            LOGGER.error("Error occurred when trying to check utility validity at place order", e);
        }

        if (consumerUserModel != null) {
            try {
                OrderModel orderModel = consumerOrderPlacementService.placeOrder(id, consumerOrderModel);
                return ResponseEntity.ok().body(orderModel);
            } catch (OrderPlacementException e) {
                LOGGER.error("Error occurred when trying to place order", e);
                return ResponseEntity.internalServerError().build();
            }
        }

        return ResponseEntity.badRequest().body("Consumer id is invalid.");
    }
}
