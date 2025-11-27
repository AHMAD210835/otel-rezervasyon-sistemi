package otelrezervasyon;

public class Reservation {

    private int reservationId;
    private Room room;
    private Customer customer;
    private int nights;
    private double totalPrice;
    private boolean cancelled;

    public Reservation(int reservationId, Room room, Customer customer, int nights, double totalPrice) {
        this.reservationId = reservationId;
        this.room = room;
        this.customer = customer;
        this.nights = nights;
        this.totalPrice = totalPrice;
        this.cancelled = false;
    }

    public int getReservationId() {
        return reservationId;
    }

    public Room getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getNights() {
        return nights;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
