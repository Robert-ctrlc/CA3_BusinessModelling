
public class Basket {

private Item product;
private int amount;
private double totalCost;

public Basket(Item product,int amount,double totalCost) {
	this.product = product;
	this.amount = amount;
	this.totalCost = totalCost;
}

public Item getProduct() {
	return product;
}
public void setProduct(Item product) {
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
