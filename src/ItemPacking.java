import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemPacking extends JFrame {

    private JTextField weightTextField;
    private JTextField heightTextField;
    private JTextField widthTextField;
    private JTextField depthTextField;
    private JTextArea resultTextArea;

    public ItemPacking() {
        super("Item Packing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);

        // Create the main panel with a GridBagLayout.
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

        // Weight Input
        JLabel weightLabel = new JLabel("Weight of the item/s:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(weightLabel, gbc);

        weightTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(weightTextField, gbc);

        // Calculate Dimensions Heading
        JLabel dimensionsLabel = new JLabel("Calculate dimensions of item/s:");
        dimensionsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(dimensionsLabel, gbc);

        // Height Input
        JLabel heightLabel = new JLabel("Height:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(heightLabel, gbc);

        heightTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(heightTextField, gbc);

        // Width Input
        JLabel widthLabel = new JLabel("Width:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(widthLabel, gbc);

        widthTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(widthTextField, gbc);

        // Depth Input
        JLabel depthLabel = new JLabel("Depth:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(depthLabel, gbc);

        depthTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(depthTextField, gbc);

        // Calculate Item Average Button
        JButton calculateButton = new JButton("Calculate Item Package");
        calculateButton.addActionListener(this::calculateItemPackage);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(calculateButton, gbc);

        // Result TextArea
        resultTextArea = new JTextArea(5, 30);
        resultTextArea.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panel.add(resultTextArea, gbc);

        // Home Button in the Top Right Corner
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        JButton homeButton = new JButton("Home");

        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(homeButton);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage().setVisible(true);
                dispose();
            }
        });


        // Set layout and add components to the frame
        add(toolBar, BorderLayout.NORTH);

        add(panel);
        setVisible(true);
    }

    private void calculateItemPackage(ActionEvent e) {
        try {
            double weight = Double.parseDouble(weightTextField.getText());
            double height = Double.parseDouble(heightTextField.getText());
            double width = Double.parseDouble(widthTextField.getText());
            double depth = Double.parseDouble(depthTextField.getText());
    
            int smallBoxCount = 0;
            int mediumBoxCount = 0;
            int largeBoxCount = 0;
    
            // Calculate the number of boxes needed, prioritising large boxes
            while (weight > 0 || height > 0 || width > 0 || depth > 0) {
                if (weight >= 30 && height >= 30 && width >= 30 && depth >= 30) {
                    largeBoxCount++;
                    weight -= 30;
                    height -= 30;
                    width -= 30;
                    depth -= 30;
                } else if (weight >= 20  && height >= 20 && width >= 20 && depth >= 20) {
                    mediumBoxCount++;
                    weight -= 20;
                    height -= 20;
                    width -= 20;
                    depth -= 20;
                } else if (weight >= 10 && height >= 10 && width >= 10 && depth >= 10) {
                    smallBoxCount++;
                    weight -= 10;
                    height -= 10;
                    width -= 10;
                    depth -= 10;
                } else {
                    // If none of the box sizes fit, break the loop to avoid infinite loop
                    break;
                }
            }
    
            // Display the result in the text area
            resultTextArea.setText("Total number of boxes used:\n");
            resultTextArea.append("Small Boxes: " + smallBoxCount + "\n");
            resultTextArea.append("Medium Boxes: " + mediumBoxCount + "\n");
            resultTextArea.append("Large Boxes: " + largeBoxCount);
        } catch (NumberFormatException ex) {
            resultTextArea.setText("Please enter valid numeric values for dimensions.");
        }
    }
    

    public static void main(String[] args) {
        new ItemPacking();
    }
}
