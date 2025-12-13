package otelrezervasyon;

import javax.swing.*;
import java.awt.*;

public class CancelReservationWindow extends JFrame {

    private Hotel hotel;

    private JTextField reservationIdField;
    private JButton cancelButton;

    public CancelReservationWindow(Hotel hotel) {
        this.hotel = hotel;
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Rezervasyon İptali");
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel headerLabel = new JLabel("Rezervasyon İptal Et", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.add(new JLabel("Rezervasyon ID:"));
        reservationIdField = new JTextField();
        centerPanel.add(reservationIdField);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        cancelButton = new JButton("İptal et");
        cancelButton.addActionListener(e -> handleCancelReservation());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(cancelButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private void handleCancelReservation() {
        String idText = reservationIdField.getText().trim();

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Lütfen rezervasyon ID girin.",
                    "Hata",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int reservationId;

        try {
            reservationId = Integer.parseInt(idText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Rezervasyon ID sayı olmalıdır.",
                    "Hata",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = hotel.cancelReservation(reservationId);

        if (success) {
            JOptionPane.showMessageDialog(this,
                    "Rezervasyon başarıyla iptal edildi.",
                    "Başarılı",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose(); // pencereyi kapat
        } else {
            JOptionPane.showMessageDialog(this,
                    "Rezervasyon bulunamadı veya zaten iptal edilmiş.",
                    "Bilgi",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
