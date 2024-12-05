import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckBalance extends JPanel implements ActionListener {
    JButton submitButton;
    JTextField pinTextField;
    JTextField textField2; // To display balance
    protected String name;

    CheckBalance(String name) {
        this.name = name;
        setBounds(50, 550, 400, 400);
        setBackground(Color.CYAN);

        JLabel label = new JLabel("ENTER PIN");
        label.setFont(new Font("System", Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        label.setBounds(10, 10, 400, 20);
        add(label);

        pinTextField = new JTextField();
        pinTextField.setBounds(10, 40, 320, 25);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 22));
        pinTextField.setBackground(Color.white);
        add(pinTextField);

        submitButton = new JButton("SUBMIT");
        submitButton.setBounds(10, 80, 150, 25);
        submitButton.setBackground(Color.white);
        submitButton.addActionListener(this);
        add(submitButton);

        JLabel label2 = new JLabel("AVAILABLE BALANCE IS:");
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setForeground(Color.BLACK);
        label2.setBounds(10, 120, 400, 20);
        add(label2);

        textField2 = new JTextField();
        textField2.setBounds(10, 160, 320, 25);
        textField2.setFont(new Font("Arial", Font.BOLD, 22));
        textField2.setBackground(Color.white);
        textField2.setEditable(false); // Make it non-editable
        add(textField2);

        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String enteredPin = pinTextField.getText();

            try {
                int pin = Integer.parseInt(enteredPin); // Validate input as a number

                // Database connection
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/abc?autoReconnect=true&useSSL=false", // Update your DB URL
                        "root", // Replace with your DB username
                        "123456789" // Replace with your DB password
                );

                // Query to verify PIN and get balance
                String query = "SELECT pass, balance FROM customer WHERE name = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int storedPin = resultSet.getInt("pass");
                    double balance = resultSet.getDouble("balance");

                    if (storedPin == pin) {
                        textField2.setText(String.valueOf(balance)); // Display balance
//                        JOptionPane.showMessageDialog(this, "PIN Verified. Balance displayed.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Incorrect PIN.", "Error", JOptionPane.ERROR_MESSAGE);
                        textField2.setText(""); // Clear balance display
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Clean up resources
                resultSet.close();
                statement.close();
                connection.close();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid numeric PIN.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database connection error. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
       CheckBalance check= new CheckBalance("name");
    }
}
