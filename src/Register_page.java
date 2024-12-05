import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register_page extends JFrame implements ActionListener {

    JButton submitButton,loginButton;
    JTextField nameTextfield, phoneNOTextfield, ageTextfield, emailTextfield;
    JPasswordField passwordField, passwordField1;
    public String name;
            String phoneNo, email, password, confirmPassword;
    int age;

    Register_page() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("reg page 1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1400, 800);
        add(l3);

        setTitle("Register_page");
        setSize(1400, 800);
        getContentPane().setBackground(new Color(150, 239, 255));

        JLabel nameLable = new JLabel("NAME :");
        nameLable.setForeground(Color.YELLOW);
        nameLable.setHorizontalAlignment(JLabel.RIGHT);
        nameLable.setFont(new Font("System", Font.BOLD, 26));
        nameLable.setBounds(800, 99, 200, 50);
        l3.add(nameLable);

        nameTextfield = new JTextField();
        nameTextfield.setBounds(1050, 100, 300, 40);
        nameTextfield.setBackground(new Color(197, 255, 248));
        nameTextfield.setFont(new Font("System", Font.BOLD, 22));
        l3.add(nameTextfield);

        JLabel ageLable = new JLabel("AGE :");
        ageLable.setForeground(Color.yellow);
        ageLable.setHorizontalAlignment(JLabel.RIGHT);
        ageLable.setFont(new Font("System", Font.BOLD, 26));
        ageLable.setBounds(800, 198, 200, 50);
        l3.add(ageLable);

        ageTextfield = new JTextField();
        ageTextfield.setBounds(1050, 200, 300, 40);
        ageTextfield.setBackground(new Color(197, 255, 248));
        ageTextfield.setFont(new Font("System", Font.BOLD, 26));
        l3.add(ageTextfield);

        JLabel phoneNoLable = new JLabel("PHONE NO :");
        phoneNoLable.setForeground(Color.yellow);
        phoneNoLable.setHorizontalAlignment(JLabel.RIGHT);
        phoneNoLable.setFont(new Font("System", Font.BOLD, 26));
        phoneNoLable.setBounds(800, 297, 200, 50);
        l3.add(phoneNoLable);

        phoneNOTextfield = new JTextField();
        phoneNOTextfield.setBounds(1050, 300, 300, 40);
        phoneNOTextfield.setBackground(new Color(197, 255, 248));
        phoneNOTextfield.setFont(new Font("System", Font.BOLD, 26));
        l3.add(phoneNOTextfield);

        JLabel emailLable = new JLabel("EMAIL :");
        emailLable.setForeground(Color.yellow);
        emailLable.setHorizontalAlignment(JLabel.RIGHT);
        emailLable.setFont(new Font("System", Font.BOLD, 26));
        emailLable.setBounds(900, 396, 100, 50);
        l3.add(emailLable);

        emailTextfield = new JTextField();
        emailTextfield.setBounds(1050, 400, 300, 40);
        emailTextfield.setBackground(new Color(197, 255, 248));
        emailTextfield.setFont(new Font("System", Font.BOLD, 26));
        l3.add(emailTextfield);

        JLabel passwordLabel = new JLabel("PASSWORD :");
        passwordLabel.setForeground(Color.yellow);
        passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
        passwordLabel.setFont(new Font("System", Font.BOLD, 26));
        passwordLabel.setBounds(750, 495, 250, 50);
        l3.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(1050, 500, 300, 40);
        passwordField.setBackground(new Color(197, 255, 248));
        passwordField.setFont(new Font("System", Font.BOLD, 26));
        l3.add(passwordField);

        JLabel passwordLabel1 = new JLabel("CHECK PASS :");
        passwordLabel1.setForeground(Color.yellow);
        passwordLabel1.setHorizontalAlignment(JLabel.RIGHT);
        passwordLabel1.setFont(new Font("System", Font.BOLD, 26));
        passwordLabel1.setBounds(750, 595, 250, 50);
        l3.add(passwordLabel1);

        passwordField1 = new JPasswordField();
        passwordField1.setBounds(1050, 600, 300, 40);
        passwordField1.setBackground(new Color(197, 255, 248));
        passwordField1.setFont(new Font("System", Font.BOLD, 26));
        l3.add(passwordField1);

        submitButton = new JButton("SUBMIT");
        submitButton.setBounds(600, 690, 250, 50);
        submitButton.setBackground(new Color(243, 238, 234));
        submitButton.setFont(new Font("System", Font.BOLD, 26));
        submitButton.addActionListener(this);
        l3.add(submitButton);

//        loginButton = new JButton("LOGIN");
//        loginButton.setBounds(900, 690, 250, 50);
//        loginButton.setBackground(new Color(243, 238, 234));
//        loginButton.setFont(new Font("System", Font.BOLD, 26));
//        loginButton.addActionListener(this);
//        l3.add(loginButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new MainFrame(name);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            name = nameTextfield.getText();
            phoneNo = phoneNOTextfield.getText();
            email = emailTextfield.getText();
            password = new String(passwordField.getPassword());
            confirmPassword = new String(passwordField1.getPassword());

            String ageText = ageTextfield.getText();
            try {
                // Parse the age only if the text is not empty
                age = Integer.parseInt(ageText);
            } catch (NumberFormatException ex) {
                // Handle the case where the text is not a valid integer
                System.err.println("Invalid age format: " + ageText);
                return; // Stop processing if age is not a valid integer
            }

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            try (
//                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?autoReconnect=true&useSSL=false", "root", "");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc", "root", "123456789");
                 PreparedStatement ps = con.prepareStatement("INSERT INTO CUSTOMER (name, age, phoneNumebr, email, pass,checkPass,balance ) values (?, ?, ?, ?, ?, ?,?)")) {
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.setString(3, phoneNo);
                ps.setString(4, email);
                ps.setString(5, password);
                ps.setString(6, confirmPassword);
                ps.setInt(7,0);
                ps.executeUpdate();
            } catch (SQLException s) {
                s.printStackTrace();
            }


        }
    }

    public static void main(String[] args) {
        new Register_page();
    }
}