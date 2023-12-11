import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShoppingBasket extends JPanel {

	private ArrayList<Product> productList;
	private ArrayList<Basket> basketList;
	private JPanel purchasePanel, headPanel, viewItemsPanel, eastPanel,itemsPanel;
	private JComboBox<String> typeBox;
	private JList<Product> displayList;
	private JScrollPane productListScrollPane;
	private JButton homeBtn, basketBtn;
	private JLabel vatLabel, priceLabel, stockLabel, nameLabel, totalCostLabel;
	private JSpinner quantityAmount;
	private JTextArea viewBasket;
	private int itemAmount;
	 private JPanel cardPanel;
	    private CardLayout cardLayout;

	public ShoppingBasket(ArrayList<Product> productList, JPanel cardPanel, CardLayout cardLayout) {
		// Initliazing my arraylists
		this.productList = productList;
		this.basketList = new ArrayList<Basket>();
	     this.cardPanel = cardPanel;
	        this.cardLayout = cardLayout;

		// Layout for panel
		setLayout(new BorderLayout());

		// Panels for use
		itemsPanel = new JPanel();
		itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS)); 
		purchasePanel = new JPanel(new BorderLayout());
		headPanel = new JPanel(new BorderLayout());
		viewItemsPanel = new JPanel(new BorderLayout());
		eastPanel = new JPanel(new GridLayout(0, 1));

		// Placeholder colors
		headPanel.setBackground(Color.GREEN);
		eastPanel.setBackground(Color.WHITE);
		
		// DefaultListModel to hold elements from arraylist and display to JList
		DefaultListModel<Product> productHolder = new DefaultListModel<>();
		for (Product product : productList) {
			productHolder.addElement(product);
		}

		// Here im creating a JList, these are handy for displaying items on a screen to be
		//selected
		displayList = new JList<>(productHolder);

		// Adding a scroll pane here for the Jlist
		productListScrollPane = new JScrollPane(displayList);

		// Adding to westpanel
		viewItemsPanel.add(productListScrollPane, BorderLayout.CENTER);

		quantityAmount = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));

		// East Panel Objects
		basketBtn = new JButton("Basket");
		vatLabel = new JLabel();
		priceLabel = new JLabel();
		stockLabel = new JLabel();
		totalCostLabel = new JLabel();
		nameLabel = new JLabel();
		eastPanel.add(basketBtn);
		eastPanel.add(nameLabel);
		eastPanel.add(vatLabel);
		eastPanel.add(priceLabel);
		eastPanel.add(stockLabel);
		eastPanel.add(totalCostLabel);
		eastPanel.add(quantityAmount);

		viewBasket = new JTextArea();
		viewBasket.setBackground(Color.ORANGE);
		// Add the westPanel to the purchase panel in the west position
		purchasePanel.add(viewItemsPanel, BorderLayout.WEST);

		// Add the eastPanel to the purchase panel in the east position
		purchasePanel.add(itemsPanel, BorderLayout.EAST);

		// HeadPanel Objects
		// Create an array to hold the item type; the combo box will filter items based
		// on their types
		String[] itemType = { "All", "Luxury", "Essential", "Gift" };
		typeBox = new JComboBox<>(itemType);
		// Home button returns to main menu
		homeBtn = new JButton("Home");
		headPanel.add(typeBox, BorderLayout.WEST);
		headPanel.add(homeBtn, BorderLayout.EAST);

		// Values to set the size of my panels
		int northPanelHeight = 50;
		int eastPanelWidth = 500;
		int westPanelWidth = 500;

		headPanel.setPreferredSize(new Dimension(headPanel.getWidth(), northPanelHeight));
		eastPanel.setPreferredSize(new Dimension(eastPanelWidth, 200));
		viewItemsPanel.setPreferredSize(new Dimension(westPanelWidth, 200)); 
		add(purchasePanel, BorderLayout.CENTER);
		add(headPanel, BorderLayout.NORTH);
		itemsPanel.add(eastPanel);
		itemsPanel.add(viewBasket);
		// Add ActionListener for my combo box
		typeBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateDisplayList();
			}
		});

		// Calls displaDetails which show all the Products details
		displayList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				displayDetails(e);
			}

		});

		// Updates the total cost based on quantity of items selected and also VAT of
		// item type
		quantityAmount.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				totalCost();
			}
		});

		// Action method when basket clicked will call and add to the basket arraylist
		basketBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addToBasket();
			}
		});
		
		 homeBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	              //Code for return to home screen!!
	            	 showMainPanel();
	            }
	        });
	       
	    }
	
//Methods for handling page actions or state changes
	// Filters items based on their type and displays them on Jlist
	private void updateDisplayList() {
		String itemType = (String) typeBox.getSelectedItem();
		//Im creating a ListModel here to hold the products 
		DefaultListModel<Product> productHolder = new DefaultListModel<>();

		for (Product product : productList) {
		    if (itemType.equals("All") || (product.getType() != null && itemType.equals(product.getType()))) {
		        productHolder.addElement(product);
		    }
		}

		displayList.setModel(productHolder);

	}

//Adds items to the basket arraylist
	private void addToBasket() {
	    // Get the selected item from the JList
	    Product selectedProduct = displayList.getSelectedValue();

	    if (selectedProduct != null) {
	        // Add the selected item to the basketList
	    	double vat = getVAT(selectedProduct);
	        itemAmount = (int) quantityAmount.getValue();
	        double totalCost = selectedProduct.getPrice() * itemAmount * vat;

	        Basket item = new Basket(selectedProduct, itemAmount, totalCost);
	        basketList.add(item);

	        // Update the basket display
	        updateBasket();

	        // Resetting spinner back to 1 when items added
	        quantityAmount.setValue(1);
	        System.out.println("Item added to basket: " + selectedProduct.getName());
	        System.out.println(basketList.size());
	    } 
	}

//Displays all product details	
	private void displayDetails(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			// Update the selectedProductLabel based on the selected item
			Product product = displayList.getSelectedValue();
			if (product != null) {
				nameLabel.setText("Selected Product: " + product.getName());
				priceLabel.setText("Item Price: " + product.getPrice());
				stockLabel.setText("In stock: " + product.getQuantity());
				
				//Resetting spinner back to 1 when switching items
				quantityAmount.setValue(1);
				
				if (product.getType().equals("Luxury")) {
					vatLabel.setText("Items VAT rate: @20%");
					totalCostLabel.setText("Total: " + product.getPrice() * 1.2);
				} else if (product.getType().equals("Essential")) {
					totalCostLabel.setText("Total: " + product.getPrice() * 1.1);
					vatLabel.setText("Items VAT rate: @10%");
				} else {
					vatLabel.setText("Items VAT rate: @5%");
					totalCostLabel.setText("Total: " + product.getPrice() * 1.05);
				}
			}
		}
	}

	private void totalCost() {
	    Product product = displayList.getSelectedValue();

	    if (product != null) {
	        itemAmount = (int) quantityAmount.getValue();
	        double price = product.getPrice();
	        double vat = getVAT(product);

	        double itemCost = price * itemAmount;
	        double vatCost = itemCost * (vat - 1);
	        double total = itemCost + vatCost;
	        totalCostLabel.setText("Total: " + total);
	    }
	}


	//Checks what type item and returns the VAT to be included in total cost
	private double getVAT(Product product) {
		String type = product.getType();
		if(type.equals("Luxury")){
			return 1.2;
		}else if(type.equals("Essential")) {
			return 1.1;
		}else {
			return 1.05;
		}
	}
	private void updateBasket() {
		//Using a stringbuilder to manipulate my JTextArea
	    StringBuilder basketBuilder = new StringBuilder("Basket:\n");

	    double totalCost = 0.0;
	    int totalQuantity = 0;

	    for (Basket basketItem : basketList) {
	    	basketBuilder.append(basketItem.getProduct().getName())
	                .append(" - Quantity: ")
	                .append(basketItem.getAmount())
	                .append(" - Item Cost: ")
	                .append(basketItem.getTotalCost())
	                .append("\n");

	        totalCost += basketItem.getTotalCost();
	        totalQuantity += basketItem.getAmount();
	    }
	   
	    basketBuilder.append("\nTotal Cost: ").append(totalCost);
	    basketBuilder.append("\nTotal Quantity: ").append(totalQuantity);

	   
	    viewBasket.setText(basketBuilder.toString());
	}

	 private void showMainPanel() {
	        cardLayout.show(cardPanel, "mainPanel");
	        setVisible(false);
	    }
}
