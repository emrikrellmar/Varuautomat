package varuautomat;

public class Dricka extends Varor {
    private int temperature;
    
    public Dricka(String name, double price, int quantity) {
        super(name, price, quantity, 0.12); 
        this.temperature = 4; 
    }
    
    public int getTemperature() { return temperature; }
    public void setTemperature(int temp) { this.temperature = temp; }
}
