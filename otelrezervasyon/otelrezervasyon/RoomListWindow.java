package otelrezervasyon;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RoomListWindow extends JFrame {

    private JTextArea textArea;

    public RoomListWindow() {
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Boş Odalar");
        setSize(500, 350);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel headerLabel = new JLabel("Boş Oda Listesi", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));

        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    // Bu metot daha sonra veriyi ekranda göstermek için kullanılacak
    public void showRooms(List<Room> rooms) {
        if (rooms == null || rooms.isEmpty()) {
            textArea.setText("Şu anda boş oda bulunmamaktadır.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Boş odalar:\n\n");

        for (Room room : rooms) {
            sb.append("Oda numarası: ")
              .append(room.getRoomNumber())
              .append(" | Gecelik fiyat: ")
              .append(room.getBasePrice())
              .append(" TL\n");
        }

        textArea.setText(sb.toString());
    }
}

