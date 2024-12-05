//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class newPin extends JPanel implements ActionListener {
//    JButton submitButton;
//    JTextField pinchangeTextField,pinchangeTextField1;
//    public String name;
//    newPin(String name) {
//        this.name=name;
//        setBounds(50, 550, 400, 400);
//        setBackground(Color.CYAN);
//
//        JLabel label2 = new JLabel("ENTER YOUR NEW PIN"); // Changed label text
//        label2.setFont(new Font("System", Font.BOLD, 16));
//        label2.setForeground(Color.black);
//        label2.setBounds(10, 70, 400, 20);
//        add(label2);
//
//        pinchangeTextField1 = new JTextField(); // Changed variable name
//        pinchangeTextField1.setBackground(new Color(65, 125, 128)); // Changed variable name
//        pinchangeTextField1.setBounds(10, 100, 320, 25); // Changed variable name
//        pinchangeTextField1.setFont(new Font("Arial", Font.BOLD, 22)); // Changed variable name
//        pinchangeTextField1.setBackground(Color.white); // Changed variable name
//        add(pinchangeTextField1); // Changed variable name
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
//
//
//
//
//    public void actionPerformed(ActionEvent a) {
//        if (a.getSource() == submitButton) {
//            String previousPin = pinchangeTextField.getText();
//
//            try {
//                double prevPin = Double.parseDouble(previousPin); // Validate input as a number
//                pinchangeTextField.setText(null); // Clear the text field
//
//                // Database connection and update logic
//                Connection connection = DriverManager.getConnection(
//                        "jdbc:mysql://localhost:3306/abc?autoReconnect=true&useSSL=false",
//                        "root",
//                        "123456789"
//                );
//
//                // Query to update the balance
//                String query = "UPDATE customer SET pass = ? WHERE name = ?";
//                PreparedStatement statement = connection.prepareStatement(query);
//                statement.setDouble(1, prevPin);
//                statement.setString(2, name);
//
//                int rowsAffected = statement.executeUpdate(); // Use executeUpdate for UPDATE queries
//                if (rowsAffected > 0) {
////                    isDeposited = true;
////                    JOptionPane.showMessageDialog(this, "Cash Deposited Successfully! Amount: " + depositAmount);
//                    newPin newpin= new newPin(name);
//                } else {
//                    String n="User not found"+name;
//                    JOptionPane.showMessageDialog(this, n, "Incorrect Previous Pin", JOptionPane.ERROR_MESSAGE);
//                }
//
//                // Clean up resources
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
//
//    }
//    public static void main(String[] args) {
////        newPin newpin = new newPin("name");
//        JFrame frame = new JFrame("Pin Change");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 400);
//        frame.add(new newPin("user_name")); // Replace "user_name" with an actual user name
//        frame.setVisible(true);
//    }
//}
//
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++





import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class newPin extends JPanel implements ActionListener {
    JButton submitButton;
    JTextField pinchangeTextField1;
    public String name;
    JFrame parentFrame;

    newPin(String name) {
        this.name = name;
        this.parentFrame = parentFrame;
        setBounds(50, 550, 400, 400);
        setBackground(Color.CYAN);

        JLabel label2 = new JLabel("ENTER YOUR NEW PIN");
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setForeground(Color.black);
        label2.setBounds(10, 70, 400, 20);
        add(label2);

        pinchangeTextField1 = new JTextField();
        pinchangeTextField1.setBounds(10, 100, 320, 25);
        pinchangeTextField1.setFont(new Font("Arial", Font.BOLD, 22));
        pinchangeTextField1.setBackground(Color.white);
        add(pinchangeTextField1);

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
            String newPin = pinchangeTextField1.getText();

            try {
                int newPinValue = Integer.parseInt(newPin); // Validate input as a number
                pinchangeTextField1.setText(null); // Clear the text field

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
            }
        }
    }
    public static void main(String[] args) {

    }

}

