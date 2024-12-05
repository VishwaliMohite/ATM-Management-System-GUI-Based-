//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.*;
//
//public class Deposit extends JPanel implements ActionListener {
//    JButton depositButton;
//    JTextField depositTextField;
//    public String name=this.name;
//    public boolean isDeposited=false;
//
//    Deposit(String name) throws InterruptedException {
//        setBounds(50, 550, 400, 400);
//        setBackground(Color.CYAN);
//
//        JLabel depositLabel = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
//        depositLabel.setFont(new Font("System", Font.BOLD, 16));
//        depositLabel.setForeground(Color.black);
//        depositLabel.setBounds(10, 10, 400, 20);
//        add(depositLabel);
//
//        depositTextField = new JTextField();
//        depositTextField.setBackground(new Color(65, 125, 128));
//        depositTextField.setBounds(10, 40, 320, 25);
//        depositTextField.setFont(new Font("Arial", Font.BOLD, 22));
//        depositTextField.setBackground(Color.white);
//        add(depositTextField);
//
//        depositButton = new JButton("DEPOSIT");
//        depositButton.setBounds(10, 80, 150, 35);
//        depositButton.setBackground(Color.white);
//        depositButton.addActionListener(this); // Add ActionListener to the button
//        add(depositButton);
//
//        setLayout(null);
//        setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent a) {
//        if (a.getSource() == depositButton) {
//            String depositAmount = depositTextField.getText();
//            depositTextField.setText(null);
////            JOptionPane.showMessageDialog(null, depositAmount + " is deposited successfully");
//
////            ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//            try {
//                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc?autoReconnect=true&useSSL=false", "root", "123456789");
//                String query = "UPDATE customer SET balance = ? WHERE name = ?";
//                PreparedStatement statement = connection.prepareStatement(query);
//                statement.setString(1, depositAmount);
//                statement.setString(2, name);
//                ResultSet resultSet = statement.executeQuery();
//
//                if (resultSet.next()) {
//                    isDeposited = true;
//                    JOptionPane.showMessageDialog(this, "Cash Deposited successful! "+depositAmount);
//                    // Redirect to main page
//                     // Replace with your main page class
//                } else {
//                    JOptionPane.showMessageDialog(this, "Invalid email or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//
//                resultSet.close();
//                statement.close();
//                connection.close();
//
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(this, "Database connection error. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//
//            }
////            ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//        }
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//          Deposit depositPanel = new Deposit("name");
//    }
//}




//New Code
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Deposit extends JPanel implements ActionListener {
    JButton depositButton;
    JTextField depositTextField;
    public String name; // Instance variable for the user's name
    private boolean isDeposited = false;

    public Deposit(String name) {

        JFrame frame = new JFrame("Deposit Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        this.name = name; // Assign the passed name to the instance variable

        // Panel setup
        setBounds(50, 550, 400, 400);
        setBackground(Color.CYAN);

        JLabel depositLabel = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        depositLabel.setFont(new Font("System", Font.BOLD, 16));
        depositLabel.setForeground(Color.black);
        depositLabel.setBounds(10, 10, 400, 20);
        add(depositLabel);

        depositTextField = new JTextField();
        depositTextField.setBounds(10, 40, 320, 25);
        depositTextField.setFont(new Font("Arial", Font.BOLD, 22));
        depositTextField.setBackground(Color.white);
        add(depositTextField);

        depositButton = new JButton("DEPOSIT");
        depositButton.setBounds(10, 80, 150, 35);
        depositButton.setBackground(Color.white);
        depositButton.addActionListener(this); // Add ActionListener to the button
        add(depositButton);

        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == depositButton) {
            String depositAmount = depositTextField.getText();

            try {
                double depositValue = Double.parseDouble(depositAmount); // Validate input as a number
                depositTextField.setText(null); // Clear the text field

                // Database connection and update logic
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/abc?autoReconnect=true&useSSL=false",
                        "root",
                        "123456789"
                );

                // Query to update the balance
                String query = "UPDATE customer SET balance = balance + ? WHERE name = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setDouble(1, depositValue);
                statement.setString(2, name);

                int rowsAffected = statement.executeUpdate(); // Use executeUpdate for UPDATE queries
                if (rowsAffected > 0) {
                    isDeposited = true;
                    JOptionPane.showMessageDialog(this, "Cash Deposited Successfully! Amount: " + depositAmount);
                } else {
                    String n="User not found"+name;
                    JOptionPane.showMessageDialog(this, n, "Error", JOptionPane.ERROR_MESSAGE);
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
        // For testing the panel


        Deposit depositPanel = new Deposit("name"); // Pass a sample name for testing

    }
}
