package varuautomat;

public class Snacks extends Varor {
    private boolean glutenFree;
    private double weightGrams;
    
    public Snacks(String name, double price, int quantity, boolean glutenFree, double weightGrams) {
        super(name, price, quantity, 0.12); // 12% moms på snacks
        this.glutenFree = glutenFree;
        this.weightGrams = weightGrams;
    }
    
    public boolean isGlutenFree() {
        return glutenFree;
    }
    
    public double getWeightGrams() {
        return weightGrams;
    }
    
    @Override
    public String toString() {
        String glutenInfo = glutenFree ? " (Glutenfri)" : "";
        return String.format("%s %.0fg%s - %.2f kr (%d st)", 
            getName(), weightGrams, glutenInfo, getPrice(), getQuantity());
    }
}