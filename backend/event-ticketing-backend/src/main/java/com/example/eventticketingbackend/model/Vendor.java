package com.example.eventticketingbackend.model;

import java.math.BigDecimal;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int totalTickets;
    private final long ticketReleaseRate;

    public Vendor(TicketPool ticketPool, int totalTickets, long ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
    }

//    @Override
//    public void run() {
//        int ticketsAdded = 0;
//        while (ticketsAdded < totalTickets) {
//            Ticket ticket = new Ticket(vendorName); // Create a new ticket with the vendor name
//            try {
//                boolean added = ticketPool.addTickets(ticket);
//                System.out.println(vendorName + " attempting to add " + ticket);
//                if (added) {
//                    ticketsAdded++;
//                    System.out.println(vendorName + " successfully added " + ticket );
//                    Thread.sleep(ticketReleaseRate * 1000); // Simulate the time delay for selling a ticket
//                }
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                System.err.println("Vendor interrupted: " + e.getMessage());
//            }
//        }
//        System.out.println("Vendor " + vendorName + " has completed selling all tickets.");
//    }

    @Override
    public void run() {
        for (int i = 1; i < totalTickets; i++) {
            Ticket ticket = new Ticket(i, "Event Simple", new BigDecimal("1500"), "Unsuccessful");
            ticketPool.addTickets(ticket);

            try {
                Thread.sleep(ticketReleaseRate * 1000);
            } catch (InterruptedException e) {
                System.out.println("Vendor interrupted: " + e.getMessage());
            }
        }
    }
}
