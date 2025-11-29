package otelrezervasyon;

public class DeluxeRoom extends Room {

    public DeluxeRoom(int roomNumber, double basePrice) {
        super(roomNumber, basePrice);
    }

    @Override
    public double calculatePrice(int nights) {
        // Deluxe oda: standart fiyattan %50 daha pahalı
        return getBasePrice() * nights * 1.5;
    }
}
