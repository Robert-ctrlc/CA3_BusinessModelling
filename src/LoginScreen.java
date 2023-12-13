import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    private JTextField userField;
    private JPasswordField passField;

    public LoginScreen() {
        // Setting up the JFrame
        setTitle("Login Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Main panel using BorderLayout
        JPanel p = new JPanel(new BorderLayout());

        // Title label
        JLabel tLabel = new JLabel("Login");
        tLabel.setFont(new Font("Arial", Font.BOLD, 30));

        // Labels, fields, and buttons
        JLabel userLabel = new JLabel("Username:");
        userField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        passField = new JPasswordField(15);
        JButton bLogin = new JButton("Login");
        JButton bHome = new JButton("Home");

        // Panel for login components using GridBagLayout
        JPanel lPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Adding components to the Login Panel
        // Adding the 'tLabel' (Login) at column 0, row 0, spanning 2 columns, aligning it to the center
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        lPanel.add(tLabel, gbc);

        // Adding the Username Label at column 0, row 1
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        lPanel.add(userLabel, gbc);

        // Adding the 'Password Label at column 0, row 2
        gbc.gridy = 2;
        lPanel.add(passLabel, gbc);

        // Adding the Login Button at column 0, row 3
        gbc.gridy = 3;
        lPanel.add(bLogin, gbc);

        // Adding the Username Text Field at column 1, row 1
        gbc.gridx = 1;
        gbc.gridy = 1;
        lPanel.add(userField, gbc);

        // Adding the Password Text Field at column 1, row 2
        gbc.gridy = 2;
        lPanel.add(passField, gbc);

        // ActionListener for the Login Button
        bLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputUser = userField.getText();
                String inputPass = new String(passField.getPassword());

                // Validating Login Credentials
                if (validateLogin(inputUser, inputPass)) {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Login successful, Welcome " + inputUser + "!");
                    
                    // Open StockHome page on successful login
                    openStockHome();
                } else {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Invalid credentials, please try again.");
                }
            }
        });

        // Adding the Login Panel to the main panel's center
        p.add(lPanel, BorderLayout.CENTER);

        // Panel for the Home Button using FlowLayout aligned to the right
        JPanel homePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        homePanel.add(bHome);
        // Adding the home Button Panel to the main panel's north (top)
        p.add(homePanel, BorderLayout.NORTH);

        // Adding the Main Panel to the Frame and making it visible
        add(p);
        setVisible(true);
    }

    // Method to validate Login Credentials
    private boolean validateLogin(String username, String password) {
        // Login successful if Password is "Root" or "root"
        return !username.isEmpty() && (password.equals("Root") || password.equals("root"));
    }

    // Method to open StockHome page
    private void openStockHome() {
        StockHome stockHome = new StockHome();
        stockHome.setVisible(true);
        dispose(); // Close the current login screen
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginScreen()); // Initialize the login window on the Event Dispatch Thread (EDT) for proper Swing concurrency
    }
}
