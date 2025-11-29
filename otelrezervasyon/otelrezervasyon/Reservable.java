package otelrezervasyon;

public interface Reservable {

    Reservation makeReservation(Customer customer, int nights, int reservationId);
}
