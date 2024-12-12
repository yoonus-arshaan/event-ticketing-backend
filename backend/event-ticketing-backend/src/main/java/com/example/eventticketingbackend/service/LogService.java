package com.example.eventticketingbackend.service;


import org.springframework.stereotype.Service;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {

    private final Path logFilePath = Path.of("logs/event-ticketing-log.log"); // Adjust the path if needed

    // Method to retrieve logs
    public List<String> getLogs() {
        try {
            if (Files.exists(logFilePath)) {
                return Files.readAllLines(logFilePath); // Read all lines from the log file
            } else {
                return new ArrayList<>(); // Return an empty list if no log file exists
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve logs", e);
        }
    }

    // Method to clear logs
    public void clearLogs() {
        try {
            if (Files.exists(logFilePath)) {
                Files.writeString(logFilePath, ""); // Clear the log file by overwriting it with an empty string
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to clear logs", e);
        }
    }
}
