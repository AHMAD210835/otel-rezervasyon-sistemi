package otelrezervasyon;

import javax.swing.*;
import java.awt.*;

public class CancelReservationWindow extends JFrame {

    private JTextField reservationIdField;
    private JButton cancelButton;

    public CancelReservationWindow() {
        initializeGUI();
    }
    // sadece gecici mesaj ekledim
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
        cancelButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Rezervasyon iptal etme mantığı henüz uygulanmadı.",
                        "Bilgi",
                        JOptionPane.INFORMATION_MESSAGE)
        );

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(cancelButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }
}
