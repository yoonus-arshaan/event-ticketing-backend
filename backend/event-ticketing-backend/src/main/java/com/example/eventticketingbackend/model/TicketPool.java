package com.example.eventticketingbackend.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TicketPool {
    private final List<Ticket> ticketPool = Collections.synchronizedList(new LinkedList<>());
    private final int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    // Add tickets to the pool (vendor adds tickets)
    public synchronized boolean addTickets(Ticket ticket) {
        while (ticketPool.size() >= maxCapacity) {
            try {
                wait(); // Wait until there's space in the pool
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor interrupted while waiting.");
                return false;
            }
        }
        ticketPool.add(ticket);
//        System.out.println("Ticket added: " + ticket);
        notifyAll(); // Notify customers that tickets are available
        System.out.println(Thread.currentThread().getName() + " added ticket to the pool. Current size is " + ticketPool.size());
        return true;
    }

    // Remove tickets from the pool (customer buys tickets)
    public synchronized Ticket removeTicket() {
        while (ticketPool.isEmpty()) {
//            System.out.println("No tickets available, waiting...");
            try {
                wait(); // Wait until there's a ticket available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer interrupted while waiting.");
                return null;
            }
        }
        Ticket ticket = ticketPool.remove(0); // Remove and return the first available ticket
//        System.out.println("Ticket removed : " + ticket);
        notifyAll(); // Notify vendors that they can add more tickets if needed
        System.out.println(Thread.currentThread().getName() + " has bought a ticket from the pool. Current size is " + ticketPool.size());
        return ticket;
    }

    public synchronized int getTicketCount() {
        return ticketPool.size();
    }
}
