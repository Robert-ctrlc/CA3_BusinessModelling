import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class ShoppingBasket extends JPanel {

	//Placeholder arraylist
	private ArrayList<Product> productList;
	private ArrayList<Product> basketList;
	//Java swing items    
    private JPanel purchasePanel, typePanel, viewItems, totalCost, eastPanel;
    private JComboBox typeBox;
    private JScrollPane  viewItemsScrollPane, totalCostScrollPane;
    private JButton homeBtn, basketBtn;
    private JLabel label1;
   

    public ShoppingBasket(ArrayList<Product> productList) {
        this.productList = productList;

        setLayout(new BorderLayout());
        //Panels
        purchasePanel = new JPanel();
       //mainPanel.setLayout(new GridLayout(2, 2));
        typePanel = new JPanel(new BorderLayout());
        viewItems = new JPanel();
        totalCost = new JPanel();

        //Placeholder colors
        typePanel.setBackground(Color.GREEN);
        viewItems.setBackground(Color.RED);
        totalCost.setBackground(Color.YELLOW);

        //Main panel
        label1 = new JLabel("Items for sale!");
        purchasePanel.add(label1);
        
        //Basket button
        
       // String basketImg ="Basket.PNG";
       // URL imageBasket = ShoppingBasket.class.getResource(basketImg);
        basketBtn = new JButton("Basket");
        viewItems.add(basketBtn);
        
        
        //Scroll bars for viewing items and total cost
         viewItemsScrollPane = new JScrollPane(viewItems);
         totalCostScrollPane = new JScrollPane(totalCost);
        
        //East panel placeholders
        eastPanel = new JPanel(new BorderLayout());
        eastPanel.add(viewItemsScrollPane, BorderLayout.CENTER);
        eastPanel.add(totalCostScrollPane, BorderLayout.SOUTH);
        
        //Create an array to hold the item type, the combobox will filter items 
        //based on their types     
        String[] itemType = {"Luxury", "Essential", "Gift"};
        typeBox = new JComboBox<>(itemType);
        homeBtn = new JButton("Home");
        
        //Add this and home button to north panel
        typePanel.add(typeBox, BorderLayout.WEST);
        typePanel.add(homeBtn, BorderLayout.EAST);     

        // Values to set the size of my panels 
        int northPanelHeight = 50;
        int eastPanelWidth = 300; 
        int southPanelHeight = 50;

        typePanel.setPreferredSize(new Dimension(typePanel.getWidth(), northPanelHeight));
        viewItemsScrollPane.setPreferredSize(new Dimension(eastPanelWidth, viewItemsScrollPane.getHeight()));
        totalCostScrollPane.setPreferredSize(new Dimension(totalCostScrollPane.getWidth(), southPanelHeight));

        add(purchasePanel, BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);
        add(typePanel, BorderLayout.NORTH);
    }
}
