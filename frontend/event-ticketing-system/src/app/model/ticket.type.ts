export interface Ticket {
    ticketId: number;
    eventName: string;
    ticketPrice: number; // Use number for simplicity in Angular (or use BigDecimal if needed)
    isSold: string;
  }