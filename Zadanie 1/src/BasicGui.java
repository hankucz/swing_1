import javax.swing.*;
import java.awt.*;

public class BasicGui extends JFrame {
    public BasicGui() {
        // Ustawienia JFrame
        setTitle("Zadanie 1+2");
        setSize(556, 760);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tworzenie panelu z komponentami
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        // Komponenty - tu je tworze
        JLabel label = new JLabel("Wprowadź tekst:");
        JTextField textField = new JTextField();
        JLabel passwordLabel = new JLabel("Hasło:");
        JPasswordField passwordField = new JPasswordField();
        JButton button = new JButton("Wyświetl dane");
        JTextArea textArea = new JTextArea(10, 10);

        // Działanie przycisku
        button.addActionListener(e -> {
            String text = textField.getText();
            String password = new String(passwordField.getPassword());
            textArea.setText("Tekst: " + text + "\nHasło: " + password);
        });

        // Dodawanie komponentów do panelu
        panel.add(label);
        panel.add(textField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(button);

        // Dodanie do JFrame - dodaje do plikacji
        add(panel, BorderLayout.SOUTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Wyświetlenie
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BasicGui::new);
    }
}

