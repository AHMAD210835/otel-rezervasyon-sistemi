package otelrezervasyon;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Hotel hotel = createSampleHotel();

        System.out.println("Otel Rezervasyon Sistemi başlatıldı.");
        System.out.println("Otel adı: " + hotel.getName());

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("=== Otel Rezervasyon Sistemi Menüsü ===");
            System.out.println("1) Boş odaları listele");
            // ileriki günlerde diğer seçenekler eklenecek:
            // 2) Oda rezervasyonu yap
            // 3) Rezervasyon iptal et
            // 4) Rezervasyon detaylarını göster
            System.out.println("0) Çıkış");
            System.out.print("Lütfen bir seçenek seçin: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // satır sonunu temizlemek için

            switch (choice) {
                case 1:
                    listAvailableRooms(hotel);
                    break;
                case 0:
                    System.out.println("Program sonlandırılıyor. İyi günler!");
                    running = false;
                    break;
                default:
                    System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
            }
        }

        scanner.close();
    }

    // 🔹 فندق تجريبي مع شوية غرف كبداية
    private static Hotel createSampleHotel() {
        Hotel hotel = new Hotel("ChatGPT Otel");

        // Standart odalar
        hotel.addRoom(new StandardRoom(101, 1000));
        hotel.addRoom(new StandardRoom(102, 1000));

        // Deluxe odalar
        hotel.addRoom(new DeluxeRoom(201, 1500));
        hotel.addRoom(new DeluxeRoom(202, 1500));

        return hotel;
    }

    // 🔹 دالة لعرض الغرف الفارغة
    private static void listAvailableRooms(Hotel hotel) {
        List<Room> availableRooms = hotel.getAvailableRooms();

        if (availableRooms.isEmpty()) {
            System.out.println("Şu anda boş oda bulunmamaktadır.");
        } else {
            System.out.println("Boş odalar:");
            for (Room room : availableRooms) {
                System.out.println("Oda numarası: " + room.getRoomNumber()
                        + " | Gecelik fiyat: " + room.getBasePrice() + " TL");
            }
        }
    }
}
