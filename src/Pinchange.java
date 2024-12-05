//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class Pinchange extends JPanel implements ActionListener {
//    JButton submitButton;
//    JTextField pinchangeTextField;
//    public String name;
//
//    Pinchange(String name) {
//        this.name = name;
//        setBounds(50, 550, 400, 400);
//        setBackground(Color.CYAN);
//
//        JLabel label = new JLabel("ENTER YOUR PREVIOUS PIN");
//        label.setFont(new Font("System", Font.BOLD, 16));
//        label.setForeground(Color.black);
//        label.setBounds(10, 10, 400, 20);
//        add(label);
//
//        pinchangeTextField = new JTextField();
//        pinchangeTextField.setBackground(new Color(65, 125, 128));
//        pinchangeTextField.setBounds(10, 40, 320, 25);
//        pinchangeTextField.setFont(new Font("Arial", Font.BOLD, 22));
//        pinchangeTextField.setBackground(Color.white);
//        add(pinchangeTextField);
//
//        submitButton = new JButton("SUBMIT");
//        submitButton.setBounds(10, 140, 150, 35);
//        submitButton.setBackground(Color.white);
//        submitButton.addActionListener(this);
//        add(submitButton);
//
//        setLayout(null);
//        setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent a) {
//        if (a.getSource() == submitButton) {
//            String previousPin = pinchangeTextField.getText();
//
//            try {
//                int prevPin = Integer.parseInt(previousPin); // Validate input as a number
//                pinchangeTextField.setText(null); // Clear the text field
//
//                // Database connection
//                Connection connection = DriverManager.getConnection(
//                        "jdbc:mysql://localhost:3306/abc?autoReconnect=true&useSSL=false",
//                        "root",
//                        "123456789"
//                );
//
//                // Query to check if the entered PIN matches
//                String query = "SELECT pass FROM customer WHERE name = ?";
//                PreparedStatement statement = connection.prepareStatement(query);
//                statement.setString(1, name);
//
//                ResultSet resultSet = statement.executeQuery();
//                boolean isPinMatched = false;
//
//                if (resultSet.next()) {
//                    int databasePin = resultSet.getInt("pass");
//                    if (databasePin == prevPin) {
//                        isPinMatched = true;
//                    }
//                }
//
//                if (isPinMatched) {
//                    JOptionPane.showMessageDialog(this, "PIN Verified. Redirecting to New PIN Page.");
//                    newPin newpin= new newPin(name); // Navigate to newPin class
//                } else {
//                    JOptionPane.showMessageDialog(this, "Incorrect Previous PIN.", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//
//                // Clean up resources
//                resultSet.close();
//                statement.close();
//                connection.close();
//
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(this, "Please enter a valid numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(this, "Database connection error. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Pin Change");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 400);
////        frame.add(new Pinchange("user_name")); // Replace "user_name" with an actual user name
//        frame.setVisible(true);
//    }
//}
//+=++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pinchange extends JPanel implements ActionListener {
    JButton submitButton;
    JTextField pinchangeTextField;
    public String name;
    JFrame parentFrame;

    Pinchange(String name) {
        this.name = name;
        this.parentFrame = parentFrame;
        setBounds(50, 550, 400, 400);
        setBackground(Color.CYAN);

        JLabel label = new JLabel("ENTER YOUR NEW PIN");
        label.setFont(new Font("System", Font.BOLD, 16));
        label.setForeground(Color.black);
        label.setBounds(10, 10, 400, 20);
        add(label);

        pinchangeTextField = new JTextField();
        pinchangeTextField.setBounds(10, 40, 320, 25);
        pinchangeTextField.setFont(new Font("Arial", Font.BOLD, 22));
        pinchangeTextField.setBackground(Color.white);
        add(pinchangeTextField);

        submitButton = new JButton("SUBMIT");
        submitButton.setBounds(10, 140, 150, 35);
        submitButton.setBackground(Color.white);
        submitButton.addActionListener(this);
        add(submitButton);

        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == submitButton) {
            String newPin = pinchangeTextField.getText();

            try {
                int newPinValue = Integer.parseInt(newPin); // Validate input as a number
                pinchangeTextField.setText(null); // Clear the text field

                // Database connection and update logic
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/abc?autoReconnect=true&useSSL=false",
                        "root",
                        "123456789"
                );

                // Query to update the PIN
                String query = "UPDATE customer SET pass = ? WHERE name = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, newPinValue);
                statement.setString(2, name);

                int rowsAffected = statement.executeUpdate(); // Use executeUpdate for UPDATE queries
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "PIN Updated Successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update PIN.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Clean up resources
                statement.close();
                connection.close();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database connection error. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
            } }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pin Change");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new Pinchange("user_name")); // Replace "user_name" with an actual user name
        frame.setVisible(true);
    }
}
