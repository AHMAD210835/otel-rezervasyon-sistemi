package otelrezervasyon;

import javax.swing.*;
import java.awt.*;

public class ReservationFormWindow extends JFrame {

    private JTextField roomNumberField;
    private JTextField customerNameField;
    private JTextField idNumberField;
    private JTextField nightsField;

    private JButton reserveButton;

    public ReservationFormWindow() {
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
        reserveButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Rezervasyon oluşturma mantığı henüz uygulanmadı.",
                        "Bilgi",
                        JOptionPane.INFORMATION_MESSAGE)
        );

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(reserveButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }
}
