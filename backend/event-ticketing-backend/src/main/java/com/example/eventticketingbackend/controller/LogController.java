package com.example.eventticketingbackend.controller;

import com.example.eventticketingbackend.service.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    @Autowired
    private LogService logService;

    // Endpoint to retrieve logs
    @GetMapping
    public ResponseEntity<List<String>> getLogs() {
        List<String> logs = logService.getLogs();
        return ResponseEntity.ok(logs);
    }

    // Endpoint to clear logs
    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearLogs() {
        logService.clearLogs();
        return ResponseEntity.noContent().build();
    }
}
