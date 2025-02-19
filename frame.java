import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frame {
    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("My Java GUI");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        // Heading label
        JLabel label = new JLabel("Enter your name:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        // Text field
        JTextField textField = new JTextField();
        panel.add(textField);

        // Button
        JButton button = new JButton("Submit");
        panel.add(button);

        // Text area for output
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(textArea, BorderLayout.SOUTH);

        // Button action listener
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                textArea.setText("Hello, " + name + "!");
            }
        });

        // Show frame
        frame.setVisible(true);
    }
}
