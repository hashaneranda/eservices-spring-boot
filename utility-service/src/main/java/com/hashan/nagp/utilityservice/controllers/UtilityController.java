package com.hashan.nagp.utilityservice.controllers;

import com.hashan.nagp.utilityservice.models.UtilityModel;
import com.hashan.nagp.utilityservice.services.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtilityController {
    @Autowired
    private UtilityService utilityService;

    @GetMapping("/utilities")
    public List<UtilityModel> getAllServices() {
        return utilityService.getAllUtilities();
    }


    @GetMapping("/utilities/{id}")
    public ResponseEntity<Object> getService(@PathVariable("id") String id) {
        UtilityModel service = utilityService.getUtilityById(id);

        if (service != null) {
            return ResponseEntity.ok().body(service);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/utilities")
    public ResponseEntity<Object> addService(@RequestBody UtilityModel serviceModel) {
        UtilityModel service = utilityService.getUtilityById(serviceModel.getId());

        if (service != null) {
            return ResponseEntity.status(403).body("Utility already exists");
        }

        UtilityModel serviceModelResult = utilityService.addUtility(serviceModel.getId(), serviceModel.getName());
        return ResponseEntity.ok().body(serviceModelResult);
    }

    @PutMapping("/utilities")
    public ResponseEntity<Object> updateService(@RequestBody UtilityModel serviceModel) {
        UtilityModel serviceModelResult = utilityService.updateUtility(serviceModel.getId(), serviceModel.getName());
        return ResponseEntity.ok().body(serviceModelResult);
    }

    @DeleteMapping("/utilities/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable("id") String id) {
        UtilityModel service = utilityService.getUtilityById(id);

        if (service != null) {
            utilityService.deleteUtility(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
