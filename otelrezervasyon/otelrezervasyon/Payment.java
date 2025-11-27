package otelrezervasyon;

public class Payment {

    private Reservation reservation;
    private boolean paid;

    public Payment(Reservation reservation) {
        this.reservation = reservation;
        this.paid = false;
    }

    public void markAsPaid() {
        this.paid = true;
    }

    public boolean isPaid() {
        return paid;
    }
}
