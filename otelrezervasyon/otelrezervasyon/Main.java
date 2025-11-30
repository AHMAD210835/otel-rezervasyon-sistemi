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
            System.out.println("2) Oda rezervasyonu yap");
            System.out.println("3) Rezervasyon iptal et");
            System.out.println("0) Çıkış");
            System.out.print("Lütfen bir seçenek seçin: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // satır sonunu temizlemek için

            switch (choice) {
                case 1:
                    listAvailableRooms(hotel);
                    break;
                case 2:
                    handleReservation(hotel, scanner);
                    break;
                case 3:
                    handleCancellation(hotel, scanner);
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

    // 🔹 Test için örnek bir otel ve odalar oluşturuyoruz
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

    // 🔹 Boş odaları listeler
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

    // 🔹 Oda rezervasyonu yapma akışı
    private static void handleReservation(Hotel hotel, Scanner scanner) {

        // Önce boş odaları gösterelim
        List<Room> availableRooms = hotel.getAvailableRooms();

        if (availableRooms.isEmpty()) {
            System.out.println("Malesef şu anda boş oda yok. Rezervasyon yapılamıyor.");
            return;
        }

        System.out.println("Rezervasyon için uygun odalar:");
        for (Room room : availableRooms) {
            System.out.println("Oda numarası: " + room.getRoomNumber()
                    + " | Gecelik fiyat: " + room.getBasePrice() + " TL");
        }

        System.out.print("Lütfen rezervasyon yapmak istediğiniz oda numarasını girin: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // satır sonunu temizle

        Room selectedRoom = hotel.findRoomByNumber(roomNumber);

        if (selectedRoom == null) {
            System.out.println("Bu oda numarasına sahip bir oda bulunamadı.");
            return;
        }

        if (!selectedRoom.isAvailable()) {
            System.out.println("Seçilen oda şu anda müsait değil.");
            return;
        }

        System.out.print("Müşteri adını girin: ");
        String customerName = scanner.nextLine();

        System.out.print("Müşteri kimlik numarasını girin: ");
        String idNumber = scanner.nextLine();

        System.out.print("Kaç gece kalınacak? ");
        int nights = scanner.nextInt();
        scanner.nextLine(); // satır sonunu temizle

        if (nights <= 0) {
            System.out.println("Gece sayısı 0 veya negatif olamaz.");
            return;
        }

        Customer customer = new Customer(customerName, idNumber);
        Reservation reservation = hotel.makeReservation(selectedRoom, customer, nights);

        if (reservation == null) {
            System.out.println("Rezervasyon oluşturulurken bir hata oluştu.");
        } else {
            System.out.println("Rezervasyon başarıyla oluşturuldu!");
            System.out.println("Rezervasyon ID: " + reservation.getReservationId());
            System.out.println("Oda numarası: " + reservation.getRoom().getRoomNumber());
            System.out.println("Müşteri: " + reservation.getCustomer().getName());
            System.out.println("Gece sayısı: " + reservation.getNights());
            System.out.println("Toplam fiyat: " + reservation.getTotalPrice() + " TL");
        }
    }
    
    // 🔹 Rezervasyon iptali akışı
    private static void handleCancellation(Hotel hotel, Scanner scanner) {

        System.out.print("İptal etmek istediğiniz rezervasyon ID'sini girin: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // satır sonunu temizle

        boolean success = hotel.cancelReservation(reservationId);

        if (success) {
            System.out.println("Rezervasyon başarıyla iptal edildi.");
        } else {
            System.out.println("Rezervasyon bulunamadı veya zaten iptal edilmiş.");
        }
    }
}
