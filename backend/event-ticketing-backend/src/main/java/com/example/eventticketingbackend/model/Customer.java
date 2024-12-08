package com.example.eventticketingbackend.model;

public class Customer implements Runnable {
    private TicketPool ticketPool;
    private int customerRetrievalRate;
    private int quantity;
//    private String customerName;

    public Customer(TicketPool ticketPool, int customerRetrievalRate, int quantity) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
//        this.customerName = customerName;
        this.quantity = quantity;
    }

//    @Override
//    public void run() {
//        while (true) {
//
//            System.out.println("\n" + customerName + " attempting to buy a ticket...");
//            Ticket ticket = ticketPool.removeTicket(); // Customer tries to buy a ticket
//            if (ticket != null) {
//                ticket.marksAsSold();
//                System.out.println(customerName + " successfully bought " + ticket + "\n");
//            } else {
//                System.out.println(customerName + " failed to buy a ticket.");
//            }
//            try {
//                Thread.sleep(customerRetrievalRate * 1000); // Simulate the time delay for purchasing a ticket
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                System.err.println("Customer interrupted: " + e.getMessage());
//                break; // Exit if interrupted
//            }
//        }
    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = ticketPool.removeTicket();
            ticket.marksAsSold();
            System.out.println("Ticket bought by " + Thread.currentThread().getName() + " Ticket is " + ticket);

            try {
                Thread.sleep(customerRetrievalRate * 1000);
            } catch (InterruptedException e) {
                System.out.println("Customer interrupted: " + e.getMessage());
            }
        }
    }
}
