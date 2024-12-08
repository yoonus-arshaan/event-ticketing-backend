package com.example.eventticketingbackend.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Ticket {
    private int ticketId;
    private String eventName;
    private BigDecimal ticketPrice;
    private String isSold;

    public Ticket(int ticketId, String eventName, BigDecimal ticketPrice, String isSold) {
        this.ticketId = ticketId;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
        this.isSold = "Unsuccessful";
    }

    public void marksAsSold() {
        this.isSold = "Successful";
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getEventName() {
        return eventName;
    }

    public String isSold() {
        return isSold;
    }


    @Override
    public String toString() {
        return "Ticket: {ID: " + ticketId + ", Event name: '" + eventName + "', Price: " + ticketPrice + ", Status: " + isSold + "}";
    }



}
