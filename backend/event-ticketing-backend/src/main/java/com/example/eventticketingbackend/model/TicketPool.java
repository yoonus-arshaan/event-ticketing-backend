package com.example.eventticketingbackend.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TicketPool {
    private final List<Ticket> ticketPool;
    private final int maxCapacity;
    private static final Logger logger = LoggerFactory.getLogger(TicketPool.class);

    public TicketPool(int maxCapacity) {
        ticketPool = Collections.synchronizedList(new LinkedList<>());
        this.maxCapacity = maxCapacity;
    }

    // Add tickets to the pool (vendor adds tickets)
    public synchronized void addTickets(Ticket ticket) {
        while (ticketPool.size() >= maxCapacity) {
            try {
                logger.info(Thread.currentThread().getName() + " waiting to add ticket");
                wait(); // Wait until there's space in the pool
            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
                logger.warn("Vendor interrupted while waiting.");
                throw new RuntimeException(e.getMessage());
            }
        }
        this.ticketPool.add(ticket);
//        System.out.println("Ticket added: " + ticket);
        notifyAll(); // Notify customers that tickets are available
        logger.info(Thread.currentThread().getName() + " added ticket to the pool. Current ticket pool size is " + ticketPool.size());

    }

    // Remove tickets from the pool (customer buys tickets)
    public synchronized Ticket removeTicket() {
        while (ticketPool.isEmpty()) {
            try {
                wait(); // Wait until there's a ticket available
            } catch (InterruptedException e) {
                logger.warn("Customer interrupted while waiting.");
                throw new RuntimeException(e.getMessage());
//                return null;
            }
        }
        Ticket ticket = ticketPool.remove(0); // Remove and return the first available ticket
        notifyAll(); // Notify vendors that they can add more tickets if needed
        logger.info(Thread.currentThread().getName() + " has bought a ticket from the pool. Current ticket pool size is " + ticketPool.size());
        return ticket;
    }

    public synchronized List<Ticket> getTickets() {
        return new LinkedList<>(ticketPool);
    }
}
