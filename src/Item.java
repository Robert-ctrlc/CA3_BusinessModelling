
public class Item {
    private String name;
    private String type;
    private String expirationDate;
    private double price;

    public Item(String name, String type, String expirationDate, double price) {
        this.name = name;
        this.type = type;
        this.expirationDate = expirationDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public double getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return name; 
    }
}

