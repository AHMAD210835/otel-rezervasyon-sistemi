package otelrezervasyon;

public abstract class Room implements Reservable {

    private int roomNumber;
    private double basePrice;
    private boolean available;

    public Room(int roomNumber, double basePrice) {
        this.roomNumber = roomNumber;
        this.basePrice = basePrice;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public double getBasePrice() {
        return basePrice;
    }
    
    public String getRoomType() {
        return "Oda";
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Polimorfizm için: her oda tipi kendi fiyatını hesaplayacak
    public abstract double calculatePrice(int nights);

    @Override
    public Reservation makeReservation(Customer customer, int nights, int reservationId) {
        if (!available) {
            // Oda uygun değilse rezervasyon yapılamaz
            return null;
        }

        double totalPrice = calculatePrice(nights);
        Reservation reservation = new Reservation(reservationId, this, customer, nights, totalPrice);
        this.available = false; // oda artık dolu
        return reservation;
    }
}
