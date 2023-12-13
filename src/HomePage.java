import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomePage extends JFrame {

    public HomePage() {
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JButton stockControlButton = new JButton("Stock Control");
        JButton shoppingButton = new JButton("Shopping");
        JButton changeCalculatorButton = new JButton("Change Calculator");
        JButton itemPackingButton = new JButton("Item Packing");
        JButton exitButton = new JButton("Exit");

        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.add(stockControlButton);
        buttonPanel.add(shoppingButton);
        buttonPanel.add(changeCalculatorButton);
        buttonPanel.add(itemPackingButton);

        add(buttonPanel, BorderLayout.CENTER);

        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        exitPanel.add(exitButton);

        add(exitPanel, BorderLayout.NORTH);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        stockControlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginScreen();
            }
        });

        shoppingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openShoppingBasket();
            }
        });

        changeCalculatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCorrectChange();
            }
        });

        itemPackingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openItemPacking();
            }
        });
    }

    private void openLoginScreen() {
        new LoginScreen().setVisible(true);
        dispose();
    }

    private void openShoppingBasket() {
        new ShoppingBasket().setVisible(true);
        dispose();
    }

    private void openCorrectChange() {
        new CorrectChange().setVisible(true);
        dispose();
    }

    private void openItemPacking() {
        new ItemPacking().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }
}
