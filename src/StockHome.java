import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class StockHome extends JFrame {

    public ArrayList<Item> itemList;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private JTable table;

    public StockHome() {
        setTitle("Stock Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        itemList = new ArrayList<>();
        
        ImageIcon button1Icon = new ImageIcon("/Users/robert/Library/CloudStorage/OneDrive-TechnologicalUniversityDublin/BusinessComputingTU914/Year 3/Business Modelling/CA3_BusinessModelling/CA3_BusinessModelling/src/plus.png");
        ImageIcon button2Icon = new ImageIcon("/Users/robert/Library/CloudStorage/OneDrive-TechnologicalUniversityDublin/BusinessComputingTU914/Year 3/Business Modelling/CA3_BusinessModelling/CA3_BusinessModelling/src/searchImg.png");

        JButton button1 = new JButton();
        button1.setIcon(new ImageIcon(button1Icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        button1.setText("Add Items ");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddItemScreen();
            }
        });

        JButton button2 = new JButton();
        button2.setIcon(new ImageIcon(button2Icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        button2.setText("View All Items");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllItems();
            }
        });

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
                new HomePage().setVisible(true);
                dispose();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void openAddItemScreen() {
        JFrame addItemFrame = new JFrame("Add Item");
        addItemFrame.setSize(400, 300);
        addItemFrame.setLocationRelativeTo(null);

        JPanel addItemPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel nameLabel = new JLabel("Item Name:");
        JTextField nameField = new JTextField();

        JLabel typeLabel = new JLabel("Item Type:");
        String[] itemTypes = {"Luxury", "Essential", "Gift"};
        JComboBox<String> typeComboBox = new JComboBox<>(itemTypes);

        JLabel expirationLabel = new JLabel("Expiration Date:");
        JTextField expirationField = new JTextField();

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        JLabel emptyLabel = new JLabel();

        JButton addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String type = (String) typeComboBox.getSelectedItem();
                String expirationDate = expirationField.getText();
                double price = Double.parseDouble(priceField.getText());

                Item newItem = new Item(name, type, expirationDate, price);
                itemList.add(newItem);
                //This calls the add method in our connector class to pass
                //added items to shopping basket
                listClass.addItem(newItem);

                addItemFrame.dispose();
            }
        });

        addItemPanel.add(nameLabel);
        addItemPanel.add(nameField);
        addItemPanel.add(typeLabel);
        addItemPanel.add(typeComboBox);
        addItemPanel.add(expirationLabel);
        addItemPanel.add(expirationField);
        addItemPanel.add(priceLabel);
        addItemPanel.add(priceField);
        addItemPanel.add(emptyLabel);
        addItemPanel.add(addItemButton);

        addItemFrame.add(addItemPanel);
        addItemFrame.setVisible(true);
    }

    private void viewAllItems() {
        JFrame viewAllItemsFrame = new JFrame("View All Items");
        viewAllItemsFrame.setSize(800, 500);
        viewAllItemsFrame.setLocationRelativeTo(null);

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Name", "Type", "Expiry", "Price"}, 0);

        for (Item item : itemList) {
            tableModel.addRow(new Object[]{item.getName(), item.getType(), item.getExpirationDate(), item.getPrice()});
        }

        table = new JTable(tableModel);

        JButton deleteButton = new JButton("Delete");
        JButton returnButton = new JButton("Return");

        JButton removeExpiredButton = new JButton("Remove Expired");
        removeExpiredButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeExpiredItems();
                updateTable();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    itemList.remove(selectedRow);
                    tableModel.removeRow(selectedRow);
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllItemsFrame.dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(deleteButton);
        buttonPanel.add(removeExpiredButton);
        buttonPanel.add(returnButton);

        viewAllItemsFrame.setLayout(new BorderLayout());
        viewAllItemsFrame.add(new JScrollPane(table), BorderLayout.CENTER);
        viewAllItemsFrame.add(buttonPanel, BorderLayout.SOUTH);

        viewAllItemsFrame.setVisible(true);
    }

    private void removeExpiredItems() {
        String currentDate = dateFormat.format(new java.util.Date());
        itemList.removeIf(item -> isExpired(item.getExpirationDate(), currentDate));
    }

    private boolean isExpired(String expirationDate, String currentDate) {
        try {
            java.util.Date expiryDate = dateFormat.parse(expirationDate);
            java.util.Date sysDate = dateFormat.parse(currentDate);
            return sysDate.after(expiryDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void updateTable() {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        for (Item item : itemList) {
            tableModel.addRow(new Object[]{item.getName(), item.getType(), item.getExpirationDate(), item.getPrice()});
        }
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
