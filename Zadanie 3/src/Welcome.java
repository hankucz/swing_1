import javax.swing.*;

public class Welcome extends JFrame {
    public Welcome() {
        setTitle("Okno powitalne");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton greetButton = new JButton("Witaj");
        greetButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Podaj swoje imię:");
            if (name != null && !name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Witaj, " + name + "!", "Powitanie", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        add(greetButton);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Welcome::new);
    }
}
