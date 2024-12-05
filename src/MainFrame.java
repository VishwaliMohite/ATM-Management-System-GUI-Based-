import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MainFrame extends JFrame implements ActionListener {
    JTextField textField;
   public String name;
   public JButton b1, b2, b3, b4, b5, b6;
    JLabel l3;
    Login_page1 loginPage;
    Deposit depositPanel;
    Withdraw withdrawPanel;
    CheckBalance checkBalancePanel;
    Pinchange pinchangePanel;
    JPanel currentPanel = null;

    MainFrame( String name) throws InterruptedException {
        this.name=name;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ATMback.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1400, 800);
        add(l3);

        textField = new JTextField();
        textField.setBackground(new Color(65, 125, 128));
        textField.setBounds(80, 580, 320, 25);
        textField.setFont(new Font("Arial", Font.BOLD, 22));
        textField.setBackground(Color.white);
//        l3.add(textField);

        JLabel Label = new JLabel(" ");
        Label.setFont(new Font("System", Font.BOLD, 16));
        Label.setForeground(Color.white);
        Label.setBounds(100, 550, 400, 20);
        l3.add(Label);

        b1 = new JButton("DEPOSIT");
        b1.setBounds(50, 100, 150, 35);
        b1.setBackground(Color.white);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("WITHDRAW");
        b2.setBounds(350, 100, 150, 35);
        b2.setBackground(Color.white);
        b2.addActionListener(this);
        l3.add(b2);

        b3 = new JButton("CHECK BALANCE");
        b3.setBounds(50, 200, 150, 35);
        b3.setBackground(Color.white);
        b3.addActionListener(this);
        l3.add(b3);

        b4 = new JButton("PIN CHANGE");
        b4.setBounds(350, 200, 150, 35);
        b4.setBackground(Color.white);
        b4.addActionListener(this);
        l3.add(b4);

//        b5 = new JButton("BACK");
//        b5.setBounds(50, 300, 150, 35);
//        b5.setBackground(Color.white);
//        l3.add(b5);
//
//        b6 = new JButton("EXIT");
//        b6.setBounds(350, 300, 150, 35);
//        b6.setBackground(Color.white);
//        l3.add(b6);


        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b1) {
            if(pinchangePanel != null)
            {
                pinchangePanel.setVisible(false);
            }
            if(withdrawPanel !=null)
            {
                withdrawPanel.setVisible(false);
            }
            if(checkBalancePanel != null)
            {
                checkBalancePanel.setVisible(false);
            }
//            String depositAmount = textField.getText();
//            textField.setText(null);
//         JOptionPane.showMessageDialog(null, depositAmount +"is deposited successfully");
            depositPanel = new Deposit(name);
            l3.add(depositPanel);
            currentPanel = depositPanel;
            l3.revalidate();
            l3.repaint();
//     JOptionPane.showMessageDialog(null, depositAmount + " is successfully deposited");
        }
        else if (e.getSource() == b2) {


            if(pinchangePanel != null)
            {
                pinchangePanel.setVisible(false);
            }
            if(depositPanel !=null)
            {
                depositPanel.setVisible(false);
            }
            if(checkBalancePanel != null)
            {
                checkBalancePanel.setVisible(false);
            }

            String withdrawAmount = textField.getText();
            textField.setText(null);
            try {
                withdrawPanel = new Withdraw(name);
                l3.add(withdrawPanel);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            currentPanel = depositPanel;
            l3.revalidate();
            l3.repaint();
//            JOptionPane.showMessageDialog(null, withdrawAmount + " has successfully withdrawn ");
        } else if (e.getSource() == b3) {


            if(pinchangePanel != null)
            {
                pinchangePanel.setVisible(false);
            }
            if(depositPanel !=null)
            {
                depositPanel.setVisible(false);
            }
            if(withdrawPanel != null)
            {
                withdrawPanel.setVisible(false);
            }

            String balance = textField.getText();
            textField.setText(null);

            checkBalancePanel = new CheckBalance(name);
            l3.add(checkBalancePanel);
            currentPanel = depositPanel;
            l3.revalidate();
            l3.repaint();
//            JOptionPane.showMessageDialog(null, "Available balance is " + balance);
        } else if (e.getSource() == b4) {

            if(depositPanel != null)
            {
                depositPanel.setVisible(false);
            }
            if(withdrawPanel !=null)
            {
                withdrawPanel.setVisible(false);
            }
            if(checkBalancePanel != null)
            {
                checkBalancePanel.setVisible(false);
            }

            textField.setText(null);
            pinchangePanel = new Pinchange(name); // Initialize the Pinchange panel
            l3.add(pinchangePanel);              // Add to the main frame
            currentPanel = pinchangePanel;       // Update the reference to the current panel
            l3.revalidate();
            l3.repaint();
//            JOptionPane.showMessageDialog(null, "Your pin is changed successfully");
        }
//        else if (e.getSource() == b5) {
//            // Hide current panels
//            if (pinchangePanel != null) {
//                pinchangePanel.setVisible(false);
//            }
//            if (withdrawPanel != null) {
//                withdrawPanel.setVisible(false);
//            }
//            if (checkBalancePanel != null) {
//                checkBalancePanel.setVisible(false);
//            }
//            if (depositPanel != null) {
//                depositPanel.setVisible(false);
//            }
//
//            // Hide the current frame and navigate back to the login page
//            this.setVisible(false); // Hides the current main frame
//            try {
//                loginPage = new Login_page1(); // Create a new instance of Login_page1
//                loginPage.setVisible(true); // Make the login page visible
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//        } else if (e.getSource() == b6) {
//            // Exit the application by calling System.exit(0)
//            System.exit(0); // Ensures that the entire application terminates
//        }
    }

    public static void main(String[] args) throws InterruptedException {
//        new Login_page1();
//        new Register_page();

            MainFrame mainFrame = new MainFrame("name");
    }
}
