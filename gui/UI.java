package gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import varuautomat.*;

public class UI extends JFrame {
    private JPanel mainPanel;
    private StorageManager storageManager;
    private Color mainBackground = new Color(240, 240, 245);
    private Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
    private Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
    private Font buttonFont = new Font("Segoe UI", Font.BOLD, 16);
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new UI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UI() {
        setupFrame();
        initializeStorage();
        setupMainPanel();
        setVisible(true);
    }

    private void setupFrame() {
        setTitle("Vending Machine");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                storageManager.saveInventory();
                System.exit(0);
            }
        });
    }
    
    private void initializeStorage() {
        storageManager = new StorageManager();
        if (!storageManager.loadInventory()) {
            initializeDefaultInventory();
        }
    }
    
    private void initializeDefaultInventory() {
        Varor[] books = {
            new Pocketbok("Sea of tranquility", 50, 5, "Emily St. John Mandel", "Sci-Fi"),
            new Pocketbok("Station Eleven", 50, 5, "Emily St. John Mandel", "Sci-Fi"),
            new Pocketbok("The Glass Hotel", 50, 5, "Emily St. John Mandel", "Fiction")
        };
        
        Varor[] drinks = {
            new Dricka("Cola soda", 20, 10),
            new Dricka("Orange soda", 20, 10),
            new Dricka("Lime soda", 20, 10)
        };
        
        Varor[] snacks = {
            new Snacks("Chips", 10, 20, false, 200),
            new Snacks("Nötter", 10, 15, true, 150),
            new Snacks("Tuggummi", 10, 25, true, 50)
        };
        
        storageManager.setInventory("books", books);
        storageManager.setInventory("drinks", drinks);
        storageManager.setInventory("snacks", snacks);
    }
    
    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(mainBackground);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        // Title
        JLabel titleLabel = new JLabel("Varuautomat");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Välj kategori");
        subtitleLabel.setFont(mainFont);
        subtitleLabel.setForeground(Color.DARK_GRAY);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 15, 15));
        buttonPanel.setBackground(mainBackground);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        buttonPanel.setMaximumSize(new Dimension(400, 300));
        
        // Create and add buttons
        buttonPanel.add(createCategoryButton("Books", new Color(100, 149, 237)));
        buttonPanel.add(createCategoryButton("Snacks", new Color(46, 139, 87)));
        buttonPanel.add(createCategoryButton("Drinks", new Color(205, 92, 92)));
        
        // Add components to main panel
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(subtitleLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(buttonPanel);
        
        setContentPane(mainPanel);
    }
    
    private JButton createCategoryButton(String text, Color baseColor) {
        JButton button = new JButton(text);
        button.setFont(buttonFont);
        button.setForeground(Color.BLACK);
        button.setBackground(baseColor);
        button.setFocusPainted(false);
        button.setBorder(new CompoundBorder(
            new LineBorder(baseColor.darker(), 2),
            new EmptyBorder(10, 20, 10, 20)
        ));
        
        // Hover effects
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(baseColor.brighter());
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(baseColor);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
        // Add action listener
        button.addActionListener(e -> showProducts(text.toLowerCase()));
        
        return button;
    }
    
    private JButton createProductButton(Varor product, Color baseColor) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout(10, 5));
        button.setBackground(baseColor);
        button.setForeground(Color.BLACK);
        button.setBorder(new CompoundBorder(
            new LineBorder(baseColor.darker(), 1),
            new EmptyBorder(15, 20, 15, 20)
        ));
        
        // Product info panel
        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 2, 2));
        infoPanel.setOpaque(false);
        
        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(buttonFont);
        nameLabel.setForeground(Color.BLACK);
        
        JLabel priceLabel = new JLabel(String.format("Pris: %.2f kr", product.getPrice()));
        priceLabel.setFont(mainFont);
        priceLabel.setForeground(Color.BLACK);
        
        JLabel stockLabel = new JLabel(String.format("I lager: %d", product.getQuantity()));
        stockLabel.setFont(mainFont);
        stockLabel.setForeground(Color.BLACK);
        
        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(stockLabel);
        
        button.add(infoPanel, BorderLayout.CENTER);
        
        // Hover effects
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(baseColor.brighter());
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(baseColor);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
        // Add purchase action
        button.addActionListener(e -> purchaseProduct(product));
        
        return button;
    }
    
    private void showProducts(String category) {
        JPanel productsPanel = new JPanel();
        productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
        productsPanel.setBackground(mainBackground);
        productsPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        // Add title
        JLabel categoryTitle = new JLabel(category.substring(0, 1).toUpperCase() + category.substring(1));
        categoryTitle.setFont(titleFont);
        categoryTitle.setForeground(Color.BLACK);
        categoryTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        productsPanel.add(categoryTitle);
        productsPanel.add(Box.createVerticalStrut(20));
        
        // Products panel
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridLayout(0, 1, 10, 10));
        itemsPanel.setBackground(mainBackground);
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Get and add products
        Color baseColor = getColorForCategory(category);
        Varor[] products = storageManager.getInventory(category);
        for (Varor product : products) {
            itemsPanel.add(createProductButton(product, baseColor.brighter()));
        }
        
        // Add back button
        JButton backButton = createCategoryButton("Tillbaka", new Color(169, 169, 169));
        backButton.addActionListener(e -> setContentPane(mainPanel));
        
        // Add panels to scroll pane
        productsPanel.add(itemsPanel);
        productsPanel.add(Box.createVerticalStrut(20));
        productsPanel.add(backButton);
        
        // Show in scroll pane
        JScrollPane scrollPane = new JScrollPane(productsPanel);
        scrollPane.setBackground(mainBackground);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        setContentPane(scrollPane);
        revalidate();
        repaint();
    }
    
    private Color getColorForCategory(String category) {
        switch (category) {
            case "books": return new Color(100, 149, 237);
            case "snacks": return new Color(46, 139, 87);
            case "drinks": return new Color(205, 92, 92);
            default: return new Color(169, 169, 169);
        }
    }
    
    private void purchaseProduct(Varor product) {
        if (product.getQuantity() > 0) {
            String details = getProductDetails(product);
            int choice = JOptionPane.showConfirmDialog(
                this,
                details,
                "Bekräfta köpet",
                JOptionPane.YES_NO_OPTION
            );
            
            if (choice == JOptionPane.YES_OPTION) {
                product.decreaseQuantity();
                storageManager.saveInventory();
                
                JOptionPane.showMessageDialog(
                    this,
                    String.format("""
                        Köp genomfört!
                        
                        Artikel: %s
                        Pris: %.2f kr
                        """,
                        product.getName(),
                        product.getPrice(),
                        product.calculateVAT()
                    ),
                    "Tack",
                    JOptionPane.INFORMATION_MESSAGE
                );
                
                showProducts(getCategoryForProduct(product));
            }
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Förlåt, den här artikeln är slut.",
                "Slut i lager",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }
    
    private String getProductDetails(Varor product) {
        StringBuilder details = new StringBuilder("");
        details.append(String.format("Namn: %s\n", product.getName()));
        details.append(String.format("Pris: %.2f kr\n", product.getPrice()));
        
        if (product instanceof Pocketbok) {
            Pocketbok book = (Pocketbok) product;
            details.append(String.format("Författare: %s\n", book.getAuthor()));
            details.append(String.format("Genre: %s\n", book.getGenre()));
        
        details.append(String.format("\nKvar i lager: %d\n", product.getQuantity()));
        details.append("\nVill du köpa denna artikel?");
        
        return details.toString();
        }
		return null;
    }
    
    private String getCategoryForProduct(Varor product) {
        if (product instanceof Pocketbok) return "books";
        if (product instanceof Dricka) return "drinks";
        if (product instanceof Snacks) return "snacks";
        return "";
    }
}
