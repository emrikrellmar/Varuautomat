package varuautomat;

import java.io.Serializable;

public abstract class Varor implements Serializable {
    private String name;
    private double price;
    private int quantity;
    private double vatRate;
    
    public Varor(String name, double price, int quantity, double vatRate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.vatRate = vatRate;
    }
    
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public double getVatRate() { return vatRate; }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }
    
    public double calculateVAT() {
        return price * vatRate;
    }
    
    @Override
    public String toString() {
        return String.format("%s - %.2f kr (%d st)", name, price, quantity);
    }
}
