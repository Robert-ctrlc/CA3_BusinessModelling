import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CorrectChange extends JFrame {
    private JTextField totalValueField;
    private JTextField amountTakenField;
    private JTextArea changeField;

    public CorrectChange() {
        super("Change Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);

        // Create the main panel with a GridBagLayout .
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Header Label
        JLabel headerLabel = new JLabel("Please Enter The Values Below");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(headerLabel, gbc);

        // Total Value Input
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Total Value:"), gbc);
        gbc.gridx = 1;
        totalValueField = new JTextField();
        panel.add(totalValueField, gbc);

        // Amount Taken Input
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Amount Taken:"), gbc);
        gbc.gridx = 1;
        amountTakenField = new JTextField();
        panel.add(amountTakenField, gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this::calculateChange);
        buttonPanel.add(calculateButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearFields());
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        // Change Result TextArea
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        changeField = new JTextArea(10, 30);
        changeField.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(changeField);
        gbc.gridy = 5;
        panel.add(scrollPane, gbc);

        // Home Button in the Top Right Corner
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        JButton homeButton = new JButton("Home");

        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(homeButton);

        // Set layout and add components to the frame
        add(toolBar, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Event handler for the Calculate button
    private void calculateChange(ActionEvent e) {
        try {
            double totalValue = Double.parseDouble(totalValueField.getText());
            double amountTaken = Double.parseDouble(amountTakenField.getText());

            if (amountTaken >= totalValue) {
                double change = amountTaken - totalValue;

                int euros = (int) change;
                int cents = (int) Math.round((change - euros) * 100);

                int[] euroNotes = {500, 200, 100, 50, 20, 10, 5};
                int[] euroCoins = {2, 1};
                int[] euroCents = {50, 20, 10, 5, 2, 1};

                StringBuilder changeResult = new StringBuilder("Total Change Amount: " + String.format("%.2f", change) + " Euro(s)\n");
                changeResult.append("\nChange:\n");

                for (int note : euroNotes) {
                    int count = euros / note;
                    if (count > 0) {
                        changeResult.append(note).append(" Note(s): ").append(count).append("\n");
                        euros %= note;
                    }
                }

                for (int coin : euroCoins) {
                    int count = euros / coin;
                    if (count > 0) {
                        changeResult.append(coin).append(" Euro Coin(s): ").append(count).append("\n");
                        euros %= coin;
                    }
                }

                for (int cent : euroCents) {
                    int count = cents / cent;
                    if (count > 0) {
                        changeResult.append(String.format("%.2f", cent / 100.0)).append(" Cent(s): ").append(count).append("\n");
                        cents %= cent;
                    }
                }

                changeField.setText(changeResult.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Amount given must be equal or greater than total cost.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.");
        }
    }

    // Event handler for the Clear button
    private void clearFields() {
        totalValueField.setText("");
        amountTakenField.setText("");
        changeField.setText("");
    }

    // Main method to run the application
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Runnable() {
    	    public void run() {
    	        new CorrectChange();
    	    }
    	});}}