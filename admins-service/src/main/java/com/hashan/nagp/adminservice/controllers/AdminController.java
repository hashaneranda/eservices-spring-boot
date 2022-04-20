package com.hashan.nagp.adminservice.controllers;

import com.hashan.nagp.adminservice.exceptions.AssignOrder;
import com.hashan.nagp.adminservice.exceptions.ValidateParamsException;
import com.hashan.nagp.adminservice.models.OrderAssignmentModel;
import com.hashan.nagp.adminservice.services.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService orderAssignmentService;


    @PostMapping("/admin/orders")
    public ResponseEntity<Object> assignServiceProviderToOrder(@RequestBody OrderAssignmentModel orderAssignmentModel) {
        try {
            orderAssignmentService.assignOrder(orderAssignmentModel);
        } catch (AssignOrder e) {
            LOGGER.error("Error occurred when trying to assign order.", e);
            return ResponseEntity.internalServerError().build();
        } catch (ValidateParamsException e) {
            LOGGER.error("Error occurred when trying to assign order.", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
