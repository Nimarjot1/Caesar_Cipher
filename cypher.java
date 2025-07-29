import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cypher extends JFrame {

    private JTextField inputField;
    private JTextField shiftField;
    private JTextArea outputArea;

    public cypher() {
        setTitle("Caesar Cipher - Secrets of 58 BC 🛡️");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#1b1b2f"));  // Dark background

        setLayout(new BorderLayout());

        // Title panel
        JLabel title = new JLabel("📜 Secrets of Julius Caesar - 58 BC 🏛️", JLabel.CENTER);
        title.setFont(new Font("Monospaced", Font.BOLD, 24));
        title.setForeground(Color.decode("#ffcc00"));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // Story Panel
        JTextArea story = new JTextArea(
            "Around 58 BC, Julius Caesar was conquering Gaul (modern-day France & Belgium).\n" +
            "To protect his plans from enemies, he used a clever cipher: shifting each letter by 3.\n" +
            "Now, relive his tactic. Encrypt your message like a Roman general!"
        );
        story.setFont(new Font("Monospaced", Font.PLAIN, 14));
        story.setEditable(false);
        story.setForeground(Color.decode("#00ffcc"));
        story.setBackground(Color.decode("#1b1b2f"));
        story.setMargin(new Insets(10, 20, 10, 20));
        add(story, BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));
        inputPanel.setBackground(Color.decode("#1b1b2f"));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        JLabel inputLabel = new JLabel("🔤 Enter message:");
        inputLabel.setForeground(Color.WHITE);
        inputLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        inputField = new JTextField();

        JLabel shiftLabel = new JLabel("🔢 Enter shift key (0-25):");
        shiftLabel.setForeground(Color.WHITE);
        shiftLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        shiftField = new JTextField();

        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(shiftLabel);
        inputPanel.add(shiftField);

        JButton encryptButton = new JButton("🛡️ Encrypt Like Caesar!");
        encryptButton.setBackground(Color.decode("#ffcc00"));
        encryptButton.setFont(new Font("Monospaced", Font.BOLD, 14));
        encryptButton.setFocusPainted(false);
        inputPanel.add(encryptButton);

        add(inputPanel, BorderLayout.SOUTH);

        // Output Area
        outputArea = new JTextArea(5, 20);
        outputArea.setFont(new Font("Monospaced", Font.BOLD, 16));
        outputArea.setForeground(Color.decode("#00ff00"));
        outputArea.setBackground(Color.decode("#0f0f1a"));
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createTitledBorder("🗝️ Encrypted Message"));
        add(new JScrollPane(outputArea), BorderLayout.EAST);

        // Action Listener
        encryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputText = inputField.getText();
                    int shiftKey = Integer.parseInt(shiftField.getText());
                    String encrypted = encrypt(inputText, shiftKey);
                    outputArea.setText(encrypted);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid text and a shift key (0-25).", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                char shifted = (char) ((character - base + shift) % 26 + base);
                result.append(shifted);
            } else {
                result.append(character);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new cypher().setVisible(true);
        });
    }
}
