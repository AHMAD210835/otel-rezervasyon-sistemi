package otelrezervasyon;

public class Main {

    public static void main(String[] args) {

        System.out.println("Otel Rezervasyon Sistemi başlatıldı.");

        // Test amaçlı: iki farklı oda tipi oluşturuyoruz
        Room standardRoom = new StandardRoom(101, 1000);
        Room deluxeRoom = new DeluxeRoom(201, 1000);

        int nights = 3;

        double standardPrice = standardRoom.calculatePrice(nights);
        double deluxePrice = deluxeRoom.calculatePrice(nights);

        System.out.println("Standart oda (oda no: " + standardRoom.getRoomNumber() +
                ") için " + nights + " gece fiyatı: " + standardPrice + " TL");

        System.out.println("Deluxe oda (oda no: " + deluxeRoom.getRoomNumber() +
                ") için " + nights + " gece fiyatı: " + deluxePrice + " TL");
    }
}
