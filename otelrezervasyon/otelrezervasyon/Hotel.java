package otelrezervasyon;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private String name;
    private List<Room> rooms;
    private List<Reservation> reservations;
    private int nextReservationId = 1; // 🔹 rezervasyon ID'leri için sayaç

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    // 🔹 Boş odaları döndürür
    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    // 🔹 Oda numarasına göre oda bulur
    public Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    // 🔹 Yeni rezervasyon oluşturur
    public Reservation makeReservation(Room room, Customer customer, int nights) {
        if (room == null) {
            return null;
        }
        if (!room.isAvailable()) {
            return null;
        }

        int reservationId = nextReservationId++;
        Reservation reservation = room.makeReservation(customer, nights, reservationId);

        if (reservation != null) {
            reservations.add(reservation);
        }

        return reservation;
    }
}
