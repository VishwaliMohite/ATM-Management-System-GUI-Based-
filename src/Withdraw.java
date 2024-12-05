import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Withdraw extends JPanel implements ActionListener {
    JTextField withdrawTextField;
    JButton withdrawButton;
    public String name;

    Withdraw(String name) throws InterruptedException {
        this.name=name;
        setBounds(50, 550, 400, 400);
        setBackground(Color.CYAN);


        JLabel label = new JLabel("ENTER AMOUNT YOU WANT TO WITHDRAW");
        label.setFont(new Font("System", Font.BOLD, 16));
        label.setForeground(Color.black);
        label.setBounds(10, 10, 400, 20);
        add(label);

        withdrawTextField= new JTextField();
        withdrawTextField.setBackground(new Color(65, 125, 128));
        withdrawTextField.setBounds(10, 40, 320, 25);
        withdrawTextField.setFont(new Font("Arial", Font.BOLD, 22));
        withdrawTextField.setBackground(Color.white);
        add(withdrawTextField);

        withdrawButton = new JButton("WITHDRAW");
        withdrawButton.setBounds(10, 80, 150, 35);
        withdrawButton.setBackground(Color.white);
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        setLayout(null);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
        double withdrawAmt = 0;
        if (e.getSource() == withdrawButton) {
            String withdrawAmount = withdrawTextField.getText();
            withdrawAmt = Double.parseDouble(withdrawAmount);
//            +++++++++++++++++++++++++++++++++++++++++++++++++++++


            try {


                // Database connection and update logic
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/abc?autoReconnect=true&useSSL=false",
                        "root",
                        "123456789"
                );

                // Query to update the balance
                String query = "UPDATE customer SET balance = balance-? WHERE name = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setDouble(1, withdrawAmt);
                statement.setString(2, name);

                int rowsAffected = statement.executeUpdate(); // Use executeUpdate for UPDATE queries
//                if (rowsAffected > 0) {
////                    isDeposited = true;
////                    JOptionPane.showMessageDialog(this, " Amount: "+withdrawAmt+" Withdrawn Successfully!" );
//                } else {
//                    String n = "User not found" + name;
//                    JOptionPane.showMessageDialog(this, n, "Error", JOptionPane.ERROR_MESSAGE);
//                }

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
//            +++++++++++++++++++++++++++++++++++++++++++++++++++++
        withdrawTextField.setText(null);
        JOptionPane.showMessageDialog(null, " Amount: "+withdrawAmt+" Withdrawn Successfully!");
    }


    public static void main(String[] args) throws InterruptedException {
        // For testing the panel


        Withdraw withdraw = new Withdraw("name"); // Pass a sample name for testing

    }
}