package com.hashan.nagp.workerservice.controllers;

import com.hashan.nagp.workerservice.services.WorkerOrderService;
import com.hashan.nagp.workerservice.models.OrderStatusModel;
import com.hashan.nagp.workerservice.models.WorkerModel;
import com.hashan.nagp.workerservice.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for service provider.
 */
@RestController
public class WorkerController {
    @Autowired
    private WorkerService serviceProvidersService;

    @Autowired
    private WorkerOrderService serviceProviderOrderService;


    @GetMapping("/workers")
    public List<WorkerModel> getAllWorkers() {
        return serviceProvidersService.getAllWorkers();
    }


    @GetMapping("/workers/{id}")
    public ResponseEntity<Object> getWorker(@PathVariable("id") String id) {
        WorkerModel serviceProvider = serviceProvidersService.getWorkerById(id);

        if (serviceProvider != null) {
            return ResponseEntity.ok().body(serviceProvider);
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping("/workers")
    public ResponseEntity<Object> addWorker(@RequestBody WorkerModel serviceProviderModel) {
        WorkerModel serviceProvider = serviceProvidersService.getWorkerById(serviceProviderModel.getId());

        if (serviceProvider != null) {
            return ResponseEntity.status(403).body("Service provider already exists");
        }

        WorkerModel serviceProviderModelResult = serviceProvidersService.addWorker(serviceProviderModel);
        return ResponseEntity.ok().body(serviceProviderModelResult);
    }


    @PutMapping("/workers")
    public ResponseEntity<Object> updateWorker(@RequestBody WorkerModel serviceProviderModel) {
        WorkerModel serviceProviderModelResult = serviceProvidersService.updateWorker(serviceProviderModel);
        return ResponseEntity.ok().body(serviceProviderModelResult);
    }


    @DeleteMapping("/workers/{id}")
    public ResponseEntity<Object> deleteWorker(@PathVariable("id") String id) {
        WorkerModel serviceProvider = serviceProvidersService.getWorkerById(id);

        if (serviceProvider != null) {
            serviceProvidersService.deleteWorker(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping("/workers/{id}/order-status")
    public ResponseEntity<Object> addWorker(@PathVariable("id") String id,
                                            @RequestBody OrderStatusModel orderStatusModel) {
        serviceProviderOrderService.setStatus(id, orderStatusModel);
        return ResponseEntity.ok().build();
    }
}
