package com.aurionpro.model;
import com.aurionpro.model.CustomException.*;
import java.util.*;

public class Customer {
    private Cart cart;
    private List<FoodItem> menu;
    private List<Order> orders;
    private List<DeliveryPartner> deliveryPartners;
    private Scanner sc;

    // Admin-defined discount rule: e.g., 10% off if cart total >= 500
    private double discountThreshold = 500.0;   // Minimum amount to qualify
    private double discountPercent = 10.0;      // Discount percentage

    public Customer(List<FoodItem> menu, List<DeliveryPartner> deliveryPartners) {
        this.menu = menu;
        this.deliveryPartners = deliveryPartners;
        this.cart = new Cart();
        this.orders = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    // --- Getters for Admin to configure discount ---
    public double getDiscountThreshold() {
        return discountThreshold;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountThreshold(double threshold) {
        this.discountThreshold = threshold;
    }

    public void setDiscountPercent(double percent) {
        this.discountPercent = percent;
    }

    public void showMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("               CUSTOMER MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. Browse Menu");
        System.out.println("2. Search Food by Name");
        System.out.println("3. Add Item to Cart");
        System.out.println("4. View Cart");
        System.out.println("5. Place Order");
        System.out.println("6. View Previous Orders");
        System.out.println("7. Back to Main Menu");
        System.out.println("=".repeat(50));
    }

    public void start() {
        int choice = 0;

        do {
            showMenu();
            System.out.print("Enter your choice: ");

            try {
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println(" Input cannot be empty. Please enter a number.");
                    continue;
                }
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input! Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    displayMenu();
                    break;
                case 2:
                    searchFoodByName();
                    break;
                case 3:
                    addToCart();
                    break;
                case 4:
                    viewCart();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 6:
                    viewOrders();
                    break;
                case 7:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select between 1-7.");
            }
        } while (choice != 7);
    }

    private void displayMenu() {
        if (menu.isEmpty()) {
            System.out.println("Menu is currently empty.");
            return;
        }
        System.out.println("\n FULL MENU");
        System.out.println("=".repeat(60));
        System.out.printf("| %-2s | %-18s | %-10s | %-6s |\n", "ID", "NAME", "PRICE", "STOCK");
        System.out.println("=".repeat(60));
        menu.forEach(item -> System.out.printf("| %-2d | %-18s | ₹%-9.2f | %6d |\n",
                item.getId(), item.getName(), item.getPrice(), item.getStock()));
        System.out.println("=".repeat(60));
    }

    // 🔍 Search food by partial name (case-insensitive)
    private void searchFoodByName() {
        System.out.print("Enter food name to search: ");
        String query = sc.nextLine().trim().toLowerCase();

        if (query.isEmpty()) {
            System.out.println("Search term cannot be empty.");
            return;
        }

        List<FoodItem> results = menu.stream()
                .filter(item -> item.getName().toLowerCase().contains(query))
                .toList();

        if (results.isEmpty()) {
            System.out.println("No items found matching '" + query + "'");
        } else {
            System.out.println("\n SEARCH RESULTS FOR '" + query.toUpperCase() + "'");
            System.out.println("=".repeat(60));
            System.out.printf("| %-2s | %-18s | %-10s | %-6s |\n", "ID", "NAME", "PRICE", "STOCK");
            System.out.println("=".repeat(60));
            results.forEach(item -> System.out.printf("| %-2d | %-18s | ₹%-9.2f | %6d |\n",
                    item.getId(), item.getName(), item.getPrice(), item.getStock()));
            System.out.println("=".repeat(60));
        }
    }

    private void addToCart() {
        displayMenu();
        System.out.print(" Enter Food ID to add: ");
        try {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(" Input cannot be empty.");
                return;
            }
            int id = Integer.parseInt(input);

            FoodItem item = findFoodById(id);
            if (item == null) {
                throw new InvalidFoodException(" Invalid Food ID: " + id);
            }

            System.out.print("Enter quantity: ");
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(" Quantity cannot be empty.");
                return;
            }
            int qty = Integer.parseInt(input);

            cart.addItem(item, qty);
            System.out.println("Added " + qty + "x " + item.getName() + " to cart.");

        } catch (NumberFormatException e) {
            System.out.println(" Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewCart() {
        var items = cart.getItems();
        if (items.isEmpty()) {
            System.out.println(" Your cart is empty.");
            return;
        }

        System.out.println("\n YOUR CART");
        System.out.println("-".repeat(60));
        System.out.printf("%-15s %-10s %-5s %-10s\n", "ITEM", "PRICE", "QTY", "TOTAL");
        System.out.println("-".repeat(60));

        items.forEach((item, qty) ->
                System.out.printf("%-15s ₹%-9.2f x %-3d ₹%-10.2f\n",
                        item.getName(), item.getPrice(), qty, item.getPrice() * qty)
        );

        System.out.println("-".repeat(60));
        double subtotal = cart.getTotal();
        System.out.printf("%40s: ₹%.2f\n", "SUBTOTAL", subtotal);

        double discount = 0.0;
        if (subtotal >= discountThreshold) {
            discount = subtotal * (discountPercent / 100);
            System.out.printf("%40s: -₹%.2f (%.1f%% off)\n", "DISCOUNT", discount, discountPercent);
        }

        double gst = (subtotal - discount) * 0.10;
        System.out.printf("%40s: ₹%.2f (10%% GST)\n", "GST", gst);
        System.out.printf("%40s: ₹%.2f\n", "GRAND TOTAL", subtotal - discount + gst);
    }

    private void placeOrder() {
        if (cart.isEmpty()) {
            System.out.println("Cannot place order: Cart is empty.");
            return;
        }

        try {
            DeliveryPartner partner = findAvailablePartner();
            if (partner == null) {
                throw new DeliveryPartnerUnavailableException(" No delivery partner available!");
            }

            // Ask for payment method
            String paymentMethod = getPaymentChoice();

            // Calculate discount
            double applicableDiscount = cart.getTotal() >= discountThreshold ? discountPercent : 0.0;

            // Create order with payment and discount
            Order order = new Order(cart, partner, paymentMethod, applicableDiscount);
            orders.add(order);

            // Print invoice
            order.printInvoice();

            // Confirm success
            System.out.println(" Order placed successfully! Thank you for ordering with us.");
            cart.clear();

        } catch (Exception e) {
            System.out.println(" Failed to place order: " + e.getMessage());
        }
    }

    // 💳 Prompt user to choose payment method
    private String getPaymentChoice() {
        while (true) {
            System.out.println("Choose Payment Method:");
            System.out.println("1. UPI");
            System.out.println("2. Cash on Delivery (COD)");
            System.out.print("Enter choice (1 or 2): ");

            String input = sc.nextLine().trim();
            if ("1".equals(input)) {
                return "UPI";
            } else if ("2".equals(input)) {
                return "Cash";
            } else {
                System.out.println("Invalid choice! Enter 1 for UPI or 2 for Cash.");
            }
        }
    }

    private void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println(" You haven't placed any orders yet.");
            return;
        }

        System.out.println("\n YOUR ORDER HISTORY");
        for (Order order : orders) {
            order.printInvoice();
            System.out.println();
        }
    }

    private DeliveryPartner findAvailablePartner() {
        return deliveryPartners.stream()
                .filter(dp -> !dp.isAssigned())
                .findFirst()
                .orElse(null);
    }

    private FoodItem findFoodById(int id) {
        return menu.stream()
                .filter(f -> f.getId() == id)
                .findFirst()
                .orElse(null);
    }
}