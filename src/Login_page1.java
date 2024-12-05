//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.*;
//
//public class Login_page1 extends JFrame {
//    JLabel usernameLabel, passwordLabel;
//    JTextField usernameTextField;
//    JPasswordField passwordField;
//    static final int frameWidth = 1400;
//    static final int frameHeight = 800;
//    boolean isLoggedIn = false;
//
//    Login_page1() throws InterruptedException {
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("login image.jpg"));
//        Image i2 = i1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel l3 = new JLabel(i3);
//        l3.setBounds(0, 0, 1400, 800);
//        add(l3);
//
//        setTitle("LOGIN PAGE ");
//        setSize(1400, 800);
//        getContentPane().setBackground(new Color(150, 239, 255));
//
//        usernameLabel = new JLabel("USER NAME :");
//        usernameLabel.setForeground(Color.white);
//        usernameLabel.setFont(new Font("System", Font.BOLD, 26));
//        usernameLabel.setBounds(650, 300, 200, 50);
//        l3.add(usernameLabel);
//
//        usernameTextField = new JTextField();
//        usernameTextField.setBounds(850, 300, 250, 50);
//        usernameTextField.setBackground(new Color(197, 255, 248));
//        usernameTextField.setFont(new Font("System", Font.BOLD, 20));
//        l3.add(usernameTextField);
//
//        passwordLabel = new JLabel("PASSWORD :");
//        passwordLabel.setForeground(Color.white);
//        passwordLabel.setFont(new Font("System", Font.BOLD, 26));
//        passwordLabel.setBounds(650, 400, 200, 50);
//        l3.add(passwordLabel);
//
//        passwordField = new JPasswordField();
//        passwordField.setBounds(850, 400, 250, 50);
//        passwordField.setBackground(new Color(197, 255, 248));
//        passwordField.setFont(new Font("System", Font.BOLD, 22));
//        l3.add(passwordField);
//
//        JButton submitButton = new JButton("LOGIN");
//        submitButton.setBounds(780, 500, 170, 45);
//        submitButton.setBackground(new Color(197, 255, 248));
//        submitButton.setFont(new Font("System", Font.BOLD, 24));
//        l3.add(submitButton);
//
//        JButton registerButton = new JButton("REGISTER");
//        registerButton.setBounds(1000, 500, 170, 45);
//        registerButton.setBackground(new Color(197, 255, 248));
//        registerButton.setFont(new Font("System", Font.BOLD, 24));
//        l3.add(registerButton);
//
//        submitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                performLogin();
//            }
//        });
//
//        registerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Check if already registered
//                if (isLoggedIn) {
//                    JOptionPane.showMessageDialog(Login_page1.this, "You are already registered!");
//                } else {
//                    new Register_page();
//                }
//            }
//        });
//
//        setLayout(null);
//        setLocationRelativeTo(null);
//        setVisible(true);
//        setResizable(false);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Load the session status
//        isLoggedIn = getSessionStatus();
//
//        if (isLoggedIn) {
//            // If already logged in, redirect to main page
//            MainFrame mainframe = new MainFrame(); // Instantiate the main page
//            mainframe.setVisible(true); // Show the main page
//            dispose(); // Close the login page
//        }
//    }
//
//    // Method to check session status
//    private boolean getSessionStatus() {
//        boolean loggedIn = false;
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/student?autoReconnect=true&useSSL=false", "root", "");
//            String query = "SELECT * FROM sessions WHERE isLoggedIn = 1";
//            PreparedStatement statement = connection.prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                loggedIn = true;
//            }
//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return loggedIn;
//    }
//
//    // Method to update session status
//    private void updateSessionStatus(boolean loggedIn) {
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/student?autoReconnect=true&useSSL=false", "root", "");
//            String query = "UPDATE sessions SET isLoggedIn = ?";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setBoolean(1, loggedIn);
//            statement.executeUpdate();
//            statement.close();
//            connection.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    private void performLogin() {
//        String email = usernameTextField.getText();
//        String password = new String(passwordField.getPassword());
//
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/student?autoReconnect=true&useSSL=false", "root", "");
//            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, email);
//            statement.setString(2, password);
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                isLoggedIn = true;
//                updateSessionStatus(true); // Update session status
//                JOptionPane.showMessageDialog(this, "Login successful!");
//                // Redirect to main page
//                MainFrame mainframe = new MainFrame(); // Instantiate the main page
//                mainframe.setVisible(true); // Show the main page
//                dispose(); // Close the login page
//            } else {
//                JOptionPane.showMessageDialog(this, "Invalid email or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//
//            resultSet.close();
//            statement.close();
//            connection.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error during login. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        new Login_page1();
//    }
//}


//NEw code

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

// Define the Login_page1 class, which extends JFrame
public class Login_page1 extends JFrame {
    JLabel usernameLabel, passwordLabel;   // Declare UI components and variables
    JTextField usernameTextField;
    JPasswordField passwordField;
    static final int frameWidth = 1400;
    static final int frameHeight = 800;
    boolean isLoggedIn = false;

    Login_page1() throws InterruptedException {
        // Load background image safely
        JLabel l3 = new JLabel();
        try {
            ImageIcon i1 = new ImageIcon(getClass().getResource("./login image.jpg"));
            Image i2 = i1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            l3 = new JLabel(i3);
            l3.setBounds(0, 0, 1400, 800);
            add(l3);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Image not found! Please ensure 'login_image.jpg' is in the correct location.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        setTitle("LOGIN PAGE ");
        setSize(1400, 800);
        getContentPane().setBackground(new Color(150, 239, 255));

        // Username label and text field
        usernameLabel = new JLabel("USER NAME :");
        usernameLabel.setForeground(Color.white);
        usernameLabel.setFont(new Font("System", Font.BOLD, 26));
        usernameLabel.setBounds(650, 300, 200, 50);
        l3.add(usernameLabel);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(850, 300, 250, 50);
        usernameTextField.setBackground(new Color(197, 255, 248));
        usernameTextField.setFont(new Font("System", Font.BOLD, 20));
        l3.add(usernameTextField);

        // Password label and field
        passwordLabel = new JLabel("PASSWORD :");
        passwordLabel.setForeground(Color.white);
        passwordLabel.setFont(new Font("System", Font.BOLD, 26));
        passwordLabel.setBounds(650, 400, 200, 50);
        l3.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(850, 400, 250, 50);
        passwordField.setBackground(new Color(197, 255, 248));
        passwordField.setFont(new Font("System", Font.BOLD, 22));
        l3.add(passwordField);

        // Submit (Login) button
        JButton submitButton = new JButton("LOGIN");
        submitButton.setBounds(780, 500, 170, 45);
        submitButton.setBackground(new Color(197, 255, 248));
        submitButton.setFont(new Font("System", Font.BOLD, 24));
        l3.add(submitButton);

        // Register button
        JButton registerButton = new JButton("REGISTER");
        registerButton.setBounds(1000, 500, 170, 45);
        registerButton.setBackground(new Color(197, 255, 248));
        registerButton.setFont(new Font("System", Font.BOLD, 24));
        l3.add(registerButton);

        // Login button action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        // Register button action (placeholder for now)
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register_page registerPage=new Register_page();
//                JOptionPane.showMessageDialog(Login_page1.this, "Register functionality is not yet implemented.");
            }
        });

        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Check session status
        isLoggedIn = getSessionStatus();

        if (isLoggedIn) {
            JOptionPane.showMessageDialog(this, "You are already logged in! Redirecting to main page.");
            // Redirect to main page
            MainFrame mainFrame = new MainFrame(name); // Replace with your main page class
            mainFrame.setVisible(true);
            dispose();
        }
    }

    // Method to check session status
    private boolean getSessionStatus() {
        boolean loggedIn = false;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc", "root", "123456789");
            String query = "SELECT * FROM sessions WHERE isLoggedIn = 1";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                loggedIn = true;
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return loggedIn;
    }
 public  String name;
    // Method to authenticate login
     void performLogin() {
         name = usernameTextField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc?autoReconnect=true&useSSL=false", "root", "123456789");
            String query = "SELECT * FROM customer WHERE name = ? AND pass = ?";  // SQL query for authentication
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                isLoggedIn = true;
                JOptionPane.showMessageDialog(this, "Login successful! "+name);
                // Redirect to main page
                MainFrame mainFrame = new MainFrame(name); // Replace with your main page class
                mainFrame.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Login_page1();
    }
}

