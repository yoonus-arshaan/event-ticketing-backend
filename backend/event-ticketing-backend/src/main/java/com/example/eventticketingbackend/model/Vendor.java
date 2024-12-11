package com.example.eventticketingbackend.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;


public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int totalTickets;
    private final long ticketReleaseRate;
    private static final Logger logger = LoggerFactory.getLogger(Vendor.class);

    public Vendor(TicketPool ticketPool, int totalTickets, long ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
    }


    @Override
    public void run() {

        for (int i = 1; i <= totalTickets; i++) {
            Ticket ticket = new Ticket("Event Simple", new BigDecimal("1500"), "Unsuccessful");
            ticketPool.addTickets(ticket);

            try {
                Thread.sleep(ticketReleaseRate * 1000);
            } catch (InterruptedException e) {
                logger.warn("Vendor interrupted: " + e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
