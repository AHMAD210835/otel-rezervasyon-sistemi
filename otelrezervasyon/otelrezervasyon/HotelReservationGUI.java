package otelrezervasyon;

import javax.swing.*;
import java.awt.*;

public class HotelReservationGUI extends JFrame {

    private Hotel hotel;

    public HotelReservationGUI(Hotel hotel) {
        this.hotel = hotel;
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Otel Rezervasyon Sistemi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // ekranın ortasında aç

        // Ana panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Üstte başlık etiketi
        JLabel titleLabel = new JLabel(" Otel Rezervasyon Sistemi", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Ortada butonlar için panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton listRoomsButton = new JButton("Boş odaları listele");
        JButton makeReservationButton = new JButton("Oda rezervasyonu yap");
        JButton cancelReservationButton = new JButton("Rezervasyon iptal et");
        JButton showDetailsButton = new JButton("Rezervasyon detaylarını göster");

        listRoomsButton.addActionListener(e -> {
            RoomListWindow window = new RoomListWindow();
            window.showRooms(hotel.getAvailableRooms());
            window.setVisible(true);
        });

        makeReservationButton.addActionListener(e -> {
            ReservationFormWindow window = new ReservationFormWindow(hotel);
            window.setVisible(true);
        });

        cancelReservationButton.addActionListener(e -> {
            CancelReservationWindow window = new CancelReservationWindow(hotel);
            window.setVisible(true);
        });

        showDetailsButton.addActionListener(e -> {
            ReservationDetailsWindow window = new ReservationDetailsWindow(hotel);
            window.setVisible(true);
        });

        buttonPanel.add(listRoomsButton);
        buttonPanel.add(makeReservationButton);
        buttonPanel.add(cancelReservationButton);
        buttonPanel.add(showDetailsButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Alt kısımda küçük bilgi etiketi
        JLabel footerLabel = new JLabel("Konsol menüsüyle aynı işlevler GUI üzerinden sağlanacaktır.", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        mainPanel.add(footerLabel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    // GUI'yi başlatmak icin
    public static void main(String[] args) {
        
        Hotel hotel = new Hotel("ChatGPT Otel");
        hotel.addRoom(new StandardRoom(101, 1000));
        hotel.addRoom(new StandardRoom(102, 1000));
        hotel.addRoom(new DeluxeRoom(201, 1500));
        hotel.addRoom(new DeluxeRoom(202, 1500));

        SwingUtilities.invokeLater(() -> {
            HotelReservationGUI gui = new HotelReservationGUI(hotel);
            gui.setVisible(true);
        });
    }
}
