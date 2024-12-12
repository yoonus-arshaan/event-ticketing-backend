package com.example.eventticketingbackend.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Customer implements Runnable {
    private TicketPool ticketPool;
    private int customerRetrievalRate;
    private int quantity;
    private final static Logger logger = LoggerFactory.getLogger(Customer.class);


    public Customer(TicketPool ticketPool, int customerRetrievalRate, int quantity) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = ticketPool.removeTicket();
            ticket.marksAsSold();
            logger.info("Ticket bought by " + Thread.currentThread().getName() + " is " + ticket);

            try {
                Thread.sleep(customerRetrievalRate * 1000);
            } catch (InterruptedException e) {
                logger.warn("Customer interrupted: " + e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
