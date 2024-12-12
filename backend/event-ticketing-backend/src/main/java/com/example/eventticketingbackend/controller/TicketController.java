package com.example.eventticketingbackend.controller;

import com.example.eventticketingbackend.service.LogReaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final LogReaderService logReaderService;

    public TicketController(LogReaderService logReaderService) {
        this.logReaderService = logReaderService;
    }

    @GetMapping("/availability/log")
    public String getTicketAvailabilityLog() {
        return logReaderService.getLatestTicketAvailabilityLog();
    }
}