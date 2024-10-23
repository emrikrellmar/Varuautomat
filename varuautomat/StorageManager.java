package varuautomat;

import java.io.*;
import java.util.*;

public class StorageManager {
    private static final String FILENAME = "vending_inventory.csv";
    private Map<String, Varor[]> inventory;
    
    public StorageManager() {
        inventory = new HashMap<>();
    }
    
    public void setInventory(String category, Varor[] items) {
        inventory.put(category, items);
    }
    
    public Varor[] getInventory(String category) {
        return inventory.get(category);
    }
    
    public void saveInventory() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILENAME))) {
            oos.writeObject(inventory);
            System.out.println("Lager sparad");
        } catch (IOException e) {
            System.err.println("Fel på att spara lager: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    public boolean loadInventory() {
        if (!new File(FILENAME).exists()) {
            System.out.println("Inget sparat lager hittad");
            return false;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILENAME))) {
            inventory = (Map<String, Varor[]>) ois.readObject();
            System.out.println("Lager laddad framgångsrikt");
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Fel  att ladda lagret: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
