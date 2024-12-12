/**
 * REST controller for managing system configuration in the Event Ticketing Backend system.
 * <p>
 * This controller provides endpoints to save and retrieve system configurations.
 * It interacts with the {@link ConfigurationService} to perform the operations.
 * </p>
 */
package com.example.eventticketingbackend.controller;

import com.example.eventticketingbackend.model.Configuration;
import com.example.eventticketingbackend.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    /**
     * Service to handle configuration-related operations such as saving and loading.
     */
    @Autowired
    private ConfigurationService configurationService;

    /**
     * Saves the system configuration.
     * <p>
     * This endpoint accepts a {@link Configuration} object in the request body and saves it
     * using the {@link ConfigurationService}.
     * </p>
     *
     * @param configuration the configuration object to be saved
     * @return a message indicating the success of the save operation
     */
    @PostMapping("/save")
    public String configure(@RequestBody Configuration configuration) {
        configurationService.saveConfiguration(configuration);
        return "Configuration saved successfully";
    }

    /**
     * Retrieves the current system configuration.
     * <p>
     * This endpoint returns the saved {@link Configuration} object by invoking
     * the {@link ConfigurationService#loadConfiguration()} method.
     * </p>
     *
     * @return the current system configuration wrapped in a {@link ResponseEntity}
     */
    @GetMapping("/get")
    public ResponseEntity<Configuration> getConfiguration() {
        return ResponseEntity.ok(configurationService.loadConfiguration());
    }
}
