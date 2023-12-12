
public class Basket {

private Product product;
private int amount;
private double totalCost;

public Basket(Product product,int amount,double totalCost) {
	this.product = product;
	this.amount = amount;
	this.totalCost = totalCost;
}

public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public double getTotalCost() {
	return totalCost;
}
public void setTotalCost(double totalCost) {
	this.totalCost = totalCost;
}
	
}
