package com.example.eventticketingbackend.service;

import com.example.eventticketingbackend.model.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class ConfigurationService {
    private Configuration configuration;
    private static final String configFileName = "config.json";

    public void saveConfiguration(Configuration configuration) {
        // check for valid input
        if(configuration.getTotalTickets() <= 0 || configuration.getMaxTicketCapacity() <= 0) {
            throw new IllegalArgumentException("Total Tickets and Max Ticket Capacity should be positive");
        }

        // Serialize and save to a file
        // Jackson to convert object to json
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(configFileName);
            objectMapper.writeValue(file, configuration);
        } catch(IOException e) {
            throw new RuntimeException("Failed to save configuration", e);
        }

    }

    public Configuration loadConfiguration() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(configFileName), Configuration.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
