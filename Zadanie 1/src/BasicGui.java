import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;

public class BasicGui extends JFrame {
    public BasicGui() {
        // Ustawienia JFrame
        setTitle("Zadanie 1+2");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tworzenie panelu z komponentami
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        // komponenty
        JLabel label = new JLabel("Wprowadź tekst:");
        JTextField textField = new JTextField();
        JLabel passwordLabel = new JLabel("Hasło:");
        JPasswordField passwordField = new JPasswordField();
        JTextArea textArea = new JTextArea(5, 20);

        // Dodawanie komponentów do panelu
        panel.add(label);
        panel.add(textField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        // Panel z przyciskiem i układem absolutnym
        JPanel buttonPanel = new JPanel(); // Wyłączenie zarządzania układem
        JButton button = new JButton("Wyświetl dane");
        button.setBounds(50, 20, 150, 30); // Domyślna pozycja i rozmiar
        buttonPanel.add(button);

        // Działanie przycisku
        button.addActionListener(e -> {
            String text = textField.getText();
            String password = new String(passwordField.getPassword());
            panel.add(textArea);
            textArea.setText("Tekst: " + text + "\nHasło: " + password);

        });

        // Panel dolny z przyciskami zmiany wyglądu
        JPanel controlPanel = new JPanel(new FlowLayout());

        JButton changeColorButton = new JButton("Zmień kolor");
        JButton changeSizeButton = new JButton("Zmień rozmiar");
        JButton changePositionButton = new JButton("Zmień pozycję");

        // Zmiana koloru przycisku
        changeColorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Wybierz kolor", button.getBackground());
            if (newColor != null) {
                button.setBackground(newColor);
                button.setOpaque(true);
                button.setBorderPainted(false);
            }
        });

        // Zmiana rozmiaru przycisku
        changeSizeButton.addActionListener(e -> {
            String newSize = JOptionPane.showInputDialog(this, "Podaj nową szerokość i wysokość (np. 100,50):");
            if (newSize != null && newSize.contains(",")) {
                String[] sizes = newSize.split(",");
                try {
                    int width = Integer.parseInt(sizes[0].trim());
                    int height = Integer.parseInt(sizes[1].trim());
                    button.setSize(width, height);
                    button.revalidate();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Podaj prawidłowe liczby!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Zmiana pozycji przycisku
        changePositionButton.addActionListener(e -> {
            String position = JOptionPane.showInputDialog(this, "Podaj nowe współrzędne (x,y):");
            if (position != null && position.contains(",")) {
                String[] positions = position.split(",");
                try {
                    int x = Integer.parseInt(positions[0].trim());
                    int y = Integer.parseInt(positions[1].trim());
                    button.setBounds(x, y, button.getWidth(), button.getHeight());
                    button.getParent().revalidate();
                    button.getParent().repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Podaj prawidłowe liczby!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Dodanie przycisków do panelu kontrolnego
        controlPanel.add(changeColorButton);
        controlPanel.add(changeSizeButton);
        controlPanel.add(changePositionButton);

        // Dodanie paneli do JFrame
        add(panel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        // Wyświetlenie
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BasicGui::new);
    }
}

