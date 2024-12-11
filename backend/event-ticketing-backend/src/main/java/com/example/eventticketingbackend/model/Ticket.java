package com.example.eventticketingbackend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int ticketId;
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);
    @Transient
    private int id;
    private String eventName;
    private BigDecimal ticketPrice;
    private String isSold;

    public Ticket() {

    }

    public Ticket(String eventName, BigDecimal ticketPrice, String isSold) {
        this.id = ID_GENERATOR.getAndIncrement();;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
        this.isSold = "Unsuccessful";
    }

    public void marksAsSold() {
        this.isSold = "Successful";
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getIsSold() {
        return isSold;
    }

    public void setIsSold(String isSold) {
        this.isSold = isSold;
    }

    @Override
    public String toString() {
        return "Ticket: {ID: " + id + ", Event name: '" + eventName + "', Price: " + ticketPrice + ", Status: " + isSold + "}";
    }


}
