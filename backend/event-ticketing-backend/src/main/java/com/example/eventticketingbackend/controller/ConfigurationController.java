package com.example.eventticketingbackend.controller;

import com.example.eventticketingbackend.model.Configuration;
import com.example.eventticketingbackend.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @PostMapping("/save")
    public String configure(@RequestBody Configuration configuration) {
        configurationService.saveConfiguration(configuration);
        return  "Configuration saved successfully";
    }

    @GetMapping("/get")
    public ResponseEntity<Configuration> getConfiguration() {
        return ResponseEntity.ok(configurationService.loadConfiguration());
    }
}
