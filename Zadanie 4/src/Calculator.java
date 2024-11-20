import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Calculator extends JFrame {
    public Calculator() {
        setTitle("Kalkulator");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Pole wyświetlania wyniku
        JTextField display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        add(display, BorderLayout.NORTH);

        // Panel przycisków
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));
        buttonPanel.setBackground(Color.BLACK); // Tło kalkulatora

        String[] buttons = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", "C", "=", "+"};

        ActionListener listener = e -> {
            String command = e.getActionCommand();
            if ("C".equals(command)) {
                display.setText("");
            } else if ("=".equals(command)) {
                try {
                    String result = calculate(display.getText());
                    display.setText(result);
                } catch (Exception ex) {
                    display.setText("Błąd");
                }
            } else {
                display.setText(display.getText() + command);
            }
        };

        // Tworzenie przycisków
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(listener);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setFocusPainted(false); // Wyłączenie efektu kliknięcia
            button.setOpaque(true); // Włączenie pełnej przezroczystości

            // Ustawienie kolorów
            if ("C".equals(text)) {
                button.setBackground(Color.RED); // Czerwony kolor dla przycisku "C"
            } else {
                button.setBackground(Color.DARK_GRAY); // Szary kolor dla innych przycisków
            }
            button.setForeground(Color.WHITE); // Białe napisy na przyciskach

            // Ustawienie czarnego obramowania
            button.setBorder(new LineBorder(Color.BLACK, 2));

            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private String calculate(String expression) {
        try {
            String[] tokens = convertToRPN(expression); // Konwertuj na RPN
            double result = evaluateRPN(tokens); // Oblicz wynik

            // Sprawdzenie, czy wynik jest liczbą całkowitą
            if (result == (int) result) {
                return String.valueOf((int) result); // Jeśli całkowita, usuń .0
            } else {
                return String.valueOf(result); // Jeśli zmiennoprzecinkowa, wyświetl jako double
            }
        } catch (Exception e) {
            return "Błąd";
        }
    }

    private String[] convertToRPN(String expression) {
        Stack<String> operators = new Stack<>();
        Stack<String> output = new Stack<>();

        StringBuilder number = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                number.append(c); // Zbieraj liczby wielocyfrowe
            } else {
                if (number.length() > 0) {
                    output.push(number.toString());
                    number.setLength(0);
                }

                if (c == '+' || c == '-' || c == '*' || c == '/') {
                    while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(String.valueOf(c))) {
                        output.push(operators.pop());
                    }
                    operators.push(String.valueOf(c));
                }
            }
        }

        if (number.length() > 0) {
            output.push(number.toString());
        }

        while (!operators.isEmpty()) {
            output.push(operators.pop());
        }

        return output.toArray(new String[0]);
    }

    private int precedence(String op) {
        return switch (op) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            default -> 0;
        };
    }

    private double evaluateRPN(String[] tokens) {
        Stack<Double> stack = new Stack<>();
        for (String token : tokens) {
            if (isOperator(token)) {
                double b = stack.pop();
                double a = stack.pop();
                stack.push(applyOperator(a, b, token));
            } else {
                stack.push(Double.parseDouble(token));
            }
        }
        return stack.pop();
    }

    private boolean isOperator(String token) {
        return "+-*/".contains(token);
    }

    private double applyOperator(double a, double b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
