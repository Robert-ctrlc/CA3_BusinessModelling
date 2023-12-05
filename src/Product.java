
public class Product {

	private String name, type;
	private double price;
	private int quantity;
	//private String type;
	//private double price;
	
	public Product(String name,String type,int quantity,double price) {
		this.name = name;
		this.type = type;
		this.quantity = quantity;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	   @Override
	    public String toString() {
	        return this.name; 
	    }

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
