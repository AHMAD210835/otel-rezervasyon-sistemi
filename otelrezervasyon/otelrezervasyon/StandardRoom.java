package otelrezervasyon;

public class StandardRoom extends Room {

    public StandardRoom(int roomNumber, double basePrice) {
        super(roomNumber, basePrice);
    }

    @Override
    public double calculatePrice(int nights) {
        // Standart oda: sadece gecelik fiyat * gece sayısı
        return getBasePrice() * nights;
    }
}
