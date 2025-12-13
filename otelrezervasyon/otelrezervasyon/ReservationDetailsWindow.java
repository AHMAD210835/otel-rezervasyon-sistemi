package otelrezervasyon;

import javax.swing.*;
import java.awt.*;

public class ReservationDetailsWindow extends JFrame {

    private Hotel hotel;

    private JTextField reservationIdField;
    private JButton showButton;
    private JTextArea detailsArea;

    public ReservationDetailsWindow(Hotel hotel) {
        this.hotel = hotel;
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Rezervasyon Detayları");
        setSize(520, 380);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel headerLabel = new JLabel("Rezervasyon Detaylarını Göster", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        inputPanel.add(new JLabel("Rezervasyon ID:"));
        reservationIdField = new JTextField();
        inputPanel.add(reservationIdField);

        showButton = new JButton("Göster");
        showButton.addActionListener(e -> handleShowDetails());

        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(showButton, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.CENTER);

        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setPreferredSize(new Dimension(480, 240));
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private void handleShowDetails() {
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

        Reservation reservation = hotel.findReservationById(reservationId);

        if (reservation == null) {
            detailsArea.setText("");
            JOptionPane.showMessageDialog(this,
                    "Bu ID ile bir rezervasyon bulunamadı.",
                    "Bilgi",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // ayrıntıları göster
        detailsArea.setText(reservation.getdetails());
    }
}
