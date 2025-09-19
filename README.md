# Vending Machine GUI Application

This project is a **Java-based GUI application** that simulates a vending machine. The application provides a graphical interface where users can view, select, and purchase items from different categories (**Books**, **Snacks**, and **Drinks**).

The inventory is managed by a backend system that **saves and loads product data** from a local file (`vending_inventory.csv`), ensuring that stock persists between sessions.

---

## Class Overview

### 1. **UI.java** (User Interface)

`UI.java` is the main class responsible for the entire graphical interface.
It handles:

* Window layout and design
* Menu navigation for different product categories
* Purchase handling and user interaction

---

### 2. **Varor.java** (Abstract Product Class)

`Varor.java` is an abstract class representing all types of products in the system.
Each product contains:

* **Name**
* **Price**
* **Quantity**
* **Tax rate (VAT)**

---

### 3. **Pocketbok.java** (Books)

`Pocketbok.java` is a subclass of `Varor` representing books.
Additional attributes:

* **Author**
* **Genre**

---

### 4. **Dricka.java** (Drinks)

`Dricka.java` represents drink products and extends `Varor`.
Additional attribute:

* **Temperature**

---

### 5. **Snacks.java** (Snacks)

`Snacks.java` represents snack products and is a subclass of `Varor`.
Additional attributes:

* Whether the snack **contains nuts**
* **Calorie count**

---

### 6. **StorageManager.java** (Inventory Management)

`StorageManager.java` handles **loading and saving inventory data** to and from a file.
It uses a `Map` where:

* **Key:** Product category (String)
* **Value:** Array of products (`Varor[]`)

---

## Features in Detail

### 1. **Graphical Design**

* Each product category has its **own color theme** for visual clarity:

  * **Blue** for Books
  * **Green** for Snacks
  * **Red** for Drinks
* **Hover effects** and **cursor changes** improve interactivity.

---

### 2. **Inventory Management**

* Inventory is **saved to a file** when the program closes.
* Upon restart, inventory is **loaded from the file**, ensuring stock remains persistent between sessions.
* `StorageManager` ensures that the inventory in the GUI always matches the saved data.

---

### 3. **User Interaction & Purchase Logic**

* When a user selects a product, a **confirmation dialog** appears asking if they want to proceed with the purchase.
* If confirmed:

  * The product stock decreases.
  * The inventory updates automatically.
* If the product is **out of stock**, a message alerts the user.

---

## Customizing the Inventory

To customize the **default inventory** (if no saved inventory file exists), edit the `initializeDefaultInventory()` method in `UI.java`.
This method sets up default items for Books, Drinks, and Snacks.

### Example:

```java
Varor[] drinks = {
    new Dricka("Cola", 20, 10),
    new Dricka("Orange Soda", 20, 8),
};
```

This allows you to easily add, remove, or modify products before the vending machine is launched.
