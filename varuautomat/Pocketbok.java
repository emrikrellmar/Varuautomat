package varuautomat;

public class Pocketbok extends Varor {
    private String author;
    private String genre;
    
    public Pocketbok(String name, double price, int quantity, String author, String genre) {
        super(name, price, quantity, 0.06); // 6% moms på böcker
        this.author = author;
        this.genre = genre;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getGenre() {
        return genre;
    }
    
    @Override
    public String toString() {
        return String.format("%s av %s - %.2f kr (%d st)", 
            getName(), author, getPrice(), getQuantity());
    }
}
