package otelrezervasyon;

import javax.swing.*;
import java.awt.*;

public class ReservationFormWindow extends JFrame {

    private Hotel hotel;

    private JTextField roomNumberField;
    private JTextField customerNameField;
    private JTextField idNumberField;
    private JTextField nightsField;

    private JButton reserveButton;

    public ReservationFormWindow(Hotel hotel) {
        this.hotel = hotel;
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Oda Rezervasyonu");
        setSize(450, 320);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel headerLabel = new JLabel("Oda Rezervasyonu Formu", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        formPanel.add(new JLabel("Oda numarası:"));
        roomNumberField = new JTextField();
        formPanel.add(roomNumberField);

        formPanel.add(new JLabel("Müşteri adı:"));
        customerNameField = new JTextField();
        formPanel.add(customerNameField);

        formPanel.add(new JLabel("Kimlik numarası:"));
        idNumberField = new JTextField();
        formPanel.add(idNumberField);

        formPanel.add(new JLabel("Gece sayısı:"));
        nightsField = new JTextField();
        formPanel.add(nightsField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        reserveButton = new JButton("Rezervasyon yap");
        reserveButton.addActionListener(e -> handleMakeReservation());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(reserveButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private void handleMakeReservation() {
        //  Alanları oku
        String roomText = roomNumberField.getText().trim();
        String name = customerNameField.getText().trim();
        String idNumber = idNumberField.getText().trim();
        String nightsText = nightsField.getText().trim();

        //   doğrulamalar
        if (roomText.isEmpty() || name.isEmpty() || idNumber.isEmpty() || nightsText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Lütfen tüm alanları doldurun.",
                    "Hata",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int roomNumber;
        int nights;

        try {
            roomNumber = Integer.parseInt(roomText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Oda numarası sayı olmalıdır.",
                    "Hata",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            nights = Integer.parseInt(nightsText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Gece sayısı sayı olmalıdır.",
                    "Hata",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (nights <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Gece sayısı 0 veya negatif olamaz.",
                    "Hata",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3) Odayı bul
        Room room = hotel.findRoomByNumber(roomNumber);
        if (room == null) {
            JOptionPane.showMessageDialog(this,
                    "Bu oda numarasına sahip bir oda bulunamadı.",
                    "Hata",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!room.isAvailable()) {
            JOptionPane.showMessageDialog(this,
                    "Seçilen oda şu anda müsait değil.",
                    "Hata",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //  Rezervasyonu oluşturma
        Customer customer = new Customer(name, idNumber);
        Reservation reservation = hotel.makeReservation(room, customer, nights);

        if (reservation == null) {
            JOptionPane.showMessageDialog(this,
                    "Rezervasyon oluşturulurken bir hata oluştu.",
                    "Hata",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //  Başarı mesajı
        JOptionPane.showMessageDialog(this,
                "Rezervasyon başarıyla oluşturuldu!\n\n" +
                        "Rezervasyon ID: " + reservation.getReservationId() + "\n" +
                        "Oda numarası: " + reservation.getRoom().getRoomNumber() + "\n" +
                        "Müşteri: " + reservation.getCustomer().getName() + "\n" +
                        "Gece sayısı: " + reservation.getNights() + "\n" +
                        "Toplam fiyat: " + reservation.getTotalPrice() + " TL",
                "Başarılı",
                JOptionPane.INFORMATION_MESSAGE);

        // alanlari temizleme
        roomNumberField.setText("");
        customerNameField.setText("");
        idNumberField.setText("");
        nightsField.setText("");

        
    }
}
