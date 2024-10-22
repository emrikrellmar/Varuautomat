package gui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import varuautomat.*;

public class UI extends JFrame {
    private final int SIZEX = 500;
    private final int SIZEY = 500;

    private JPanel startPanel, drinkPanel, snacksPanel, bookPanel;
    private JButton btnBooks, btnSnacks, btnDrinks, btnBack;

    private Varor[] books = {
        new Pocketbok("Sea", 50, 5, "Emily St. John Mandel", "Sci-Fi"),
        new Pocketbok("Station Eleven", 50, 5, "Emily St. John Mandel", "Sci-Fi"),
        new Pocketbok("The Glass Hotel", 50, 5, "Emily St. John Mandel", "Fiction")
    };

    private Varor[] drinks = {
        new Dricka("Cola", 20, 10),
        new Dricka("Orange", 20, 10),
        new Dricka("Lime", 20, 10)
    };

    private Varor[] snacks = {
        new Snacks("Chips", 10, 20, false, 200),
        new Snacks("Nötter", 10, 15, true, 150),
        new Snacks("Tuggummi", 10, 25, true, 50)
    };

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new UI());
    }

    private UI() {
        setTitle("Varuautomat");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Stänger!");
            }
        });

        initComponents();
        setSize(SIZEX, SIZEY);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        startView();
        setVisible(true);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12)); // Mindre textstorlek
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);  // Svart text
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setPreferredSize(new Dimension(250, 40)); // Mindre storlek för knappar
        return button;
    }

    private JButton createSmallButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 10)); // Mindre textstorlek
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);  // Svart text
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Minskar border
        button.setPreferredSize(new Dimension(120, 30)); // Mindre storlek för tillbaka-knappen
        return button;
    }

    private void initComponents() {
        btnBooks = createStyledButton("Böcker", new Color(65, 105, 225));
        btnSnacks = createStyledButton("Snacks", new Color(46, 139, 87));
        btnDrinks = createStyledButton("Drycker", new Color(205, 92, 92));
        btnBack = createSmallButton("Tillbaka", new Color(169, 169, 169));

        btnBooks.addActionListener(e -> showBookPanel());
        btnSnacks.addActionListener(e -> showSnacksPanel());
        btnDrinks.addActionListener(e -> showDrinkPanel());
        btnBack.addActionListener(e -> startView());

        startPanel = new JPanel();
        drinkPanel = new JPanel();
        snacksPanel = new JPanel();
        bookPanel = new JPanel();
    }

    private JPanel createProductPanel(Varor[] products, Color color) {
        JPanel panel = new JPanel(new GridLayout(products.length + 1, 1)); // Använder GridLayout
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        for (Varor product : products) {
            JButton productButton = createStyledButton(product.getName() + " (" + product.getQuantity() + ")", color);
            productButton.addActionListener(e -> {
                purchaseProduct(product, productButton); // Skickar knappen för att uppdatera text
            });
            panel.add(productButton);
        }

        panel.add(btnBack); // Lägger till tillbaka-knappen

        return panel;
    }

    private void showDrinkPanel() {
        getContentPane().removeAll();
        setContentPane(createProductPanel(drinks, new Color(205, 92, 92)));
        validate();
        repaint();
    }

    private void showSnacksPanel() {
        getContentPane().removeAll();
        setContentPane(createProductPanel(snacks, new Color(46, 139, 87)));
        validate();
        repaint();
    }

    private void showBookPanel() {
        getContentPane().removeAll();
        setContentPane(createProductPanel(books, new Color(65, 105, 225)));
        validate();
        repaint();
    }

    private void purchaseProduct(Varor product, JButton productButton) {
        if (product.getQuantity() > 0) {
            product.decreaseQuantity();
            productButton.setText(product.getName() + " (" + product.getQuantity() + ")"); // Uppdaterar knappen
            JOptionPane.showMessageDialog(this, "Köpt: " + product.getName(), "Köp framgång", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Ej tillräcklig lager för " + product.getName(), "Köp misslyckades", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void startView() {
        getContentPane().removeAll();
        startPanel.setLayout(new GridBagLayout());
        startPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        startPanel.setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;

        gbc.gridx = 0;

        gbc.gridy = 0;
        startPanel.add(btnBooks, gbc);

        gbc.gridy = 1;
        startPanel.add(btnSnacks, gbc);

        gbc.gridy = 2;
        startPanel.add(btnDrinks, gbc);

        setContentPane(startPanel);
        validate();
        repaint();
    }
}
