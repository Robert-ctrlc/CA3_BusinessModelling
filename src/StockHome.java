import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockHome extends JFrame {

    public StockHome() {
        setTitle("Stock Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Create ImageIcons for the button images
        ImageIcon button1Icon = new ImageIcon("/Users/robert/Library/CloudStorage/OneDrive-TechnologicalUniversityDublin/BusinessComputingTU914/Year 3/Business Modelling/CA3_BusinessModelling/CA3_BusinessModelling/src/plus.png");
        ImageIcon button2Icon = new ImageIcon("/Users/robert/Library/CloudStorage/OneDrive-TechnologicalUniversityDublin/BusinessComputingTU914/Year 3/Business Modelling/CA3_BusinessModelling/CA3_BusinessModelling/src/searchImg.png");

        JButton button1 = new JButton();
        button1.setIcon(new ImageIcon(button1Icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        button1.setText("Add Items ");

        JButton button2 = new JButton();
        button2.setIcon(new ImageIcon(button2Icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        button2.setText("View All Items");

        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.add(button1);
        centerPanel.add(button2);

        add(centerPanel, BorderLayout.CENTER);

        JButton homeButton = new JButton("Home");

        JPanel homePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        homePanel.add(homeButton);

        add(homePanel, BorderLayout.NORTH);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the HomePage
                new HomePage().setVisible(true);
                // Close the current StockHome frame
                dispose();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StockHome().setVisible(true);
            }
        });
    }
}
