package com.example.eventticketingbackend.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class LogReaderService {
    private static final String LOG_FILE_PATH = "logs/event-ticketing-log.log";

    public String getLatestTicketAvailabilityLog() {
        String latestLog = "No log available";
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("Updated ticket availability")) {
                    latestLog = line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return latestLog;
    }
}
