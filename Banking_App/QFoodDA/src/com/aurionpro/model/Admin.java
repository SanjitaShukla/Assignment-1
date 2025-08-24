package com.aurionpro.model;
import java.util.*;
import com.aurionpro.model.CustomException.*;

public class Admin {
    private List<FoodItem> menu;
    private List<DeliveryPartner> deliveryPartners;
    private List<Order> orders;  // Now Admin can view sales
    private Scanner sc;

    // Discount configuration (shared with Customer)
    private double discountThreshold = 500.0;
    private double discountPercent = 10.0;

    public Admin(List<FoodItem> menu, List<DeliveryPartner> deliveryPartners, List<Order> orders) {
        this.menu = menu;
        this.deliveryPartners = deliveryPartners;
        this.orders = orders;
        this.sc = new Scanner(System.in);
    }

    // --- Getters for reporting ---
    public double getDiscountThreshold() { return discountThreshold; }
    public double getDiscountPercent() { return discountPercent; }

    public void setDiscountThreshold(double threshold) {
        if (threshold >= 0) {
            this.discountThreshold = threshold;
            System.out.println("✅ Discount threshold updated to ₹" + threshold);
        } else {
            System.out.println("❌ Threshold must be non-negative.");
        }
    }

    public void setDiscountPercent(double percent) {
        if (percent >= 0 && percent <= 100) {
            this.discountPercent = percent;
            System.out.println("Discount percentage updated to " + percent + "%");
        } else {
            System.out.println(" Discount must be between 0 and 100.");
        }
    }

    public void showMenu() {
        System.out.println("\n" + "=".repeat(55));
        System.out.println("                   ADMIN MENU");
        System.out.println("=".repeat(55));
        System.out.println("1. Add Food Item");
        System.out.println("2. Update Food Item");
        System.out.println("3. Remove Food Item");
        System.out.println("4. View Menu");
        System.out.println("5. View Delivery Partners");
        System.out.println("6. Configure Discount Policy");
        System.out.println("7. View Sales Report");
        System.out.println("8. Back to Main Menu");
        System.out.println("=".repeat(55));
    }

    public void start() {
        int choice = 0;
        do {
            showMenu();
            System.out.print("Enter your choice: ");

            try {
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("❌ Input cannot be empty.");
                    continue;
                }
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> addFoodItem();
                case 2 -> updateFoodItem();
                case 3 -> removeFoodItem();
                case 4 -> displayMenu();
                case 5 -> viewDeliveryPartners();
                case 6 -> configureDiscount();
                case 7 -> viewSalesReport();
                case 8 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice! Select 1–8.");
            }
        } while (choice != 8);
    }

    private void addFoodItem() {
        try {
            System.out.print("Enter Food ID: ");
            int id = Integer.parseInt(sc.nextLine());

            if (menu.stream().anyMatch(f -> f.getId() == id)) {
                System.out.println("Error: Food ID already exists!");
                return;
            }

            System.out.print("Enter Food Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Price: ");
            double price = Double.parseDouble(sc.nextLine());

            System.out.print("Enter Stock: ");
            int stock = Integer.parseInt(sc.nextLine());

            FoodItem item = new FoodItem(id, name, price, stock);
            menu.add(item);
            System.out.println("Food item added successfully!");

        } catch (Exception e) {
            System.out.println("Invalid input! " + e.getMessage());
        }
    }

    private void updateFoodItem() {
        displayMenu();
        System.out.print("Enter Food ID to update: ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            FoodItem item = findFoodById(id);
            if (item == null) throw new InvalidFoodException("Food not found.");

            System.out.print("New Name [" + item.getName() + "]: ");
            String name = sc.nextLine();
            if (!name.trim().isEmpty()) item.setName(name);

            System.out.print("New Price [₹" + item.getPrice() + "]: ");
            String priceStr = sc.nextLine();
            if (!priceStr.isEmpty()) item.setPrice(Double.parseDouble(priceStr));

            System.out.print("New Stock [" + item.getStock() + "]: ");
            String stockStr = sc.nextLine();
            if (!stockStr.isEmpty()) item.setStock(Integer.parseInt(stockStr));

            System.out.println("'" + item.getName() + "' updated successfully!");

        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    private void removeFoodItem() {
        displayMenu();
        System.out.print("Enter Food ID to remove: ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            FoodItem item = findFoodById(id);
            if (item == null) throw new InvalidFoodException("Food not found.");

            menu.remove(item);
            System.out.println("Removed '" + item.getName() + "' from menu.");

        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    private void displayMenu() {
        if (menu.isEmpty()) {
            System.out.println("  Menu is empty.");
            return;
        }
        System.out.println("\nnCURRENT MENU");
        System.out.println("=".repeat(60));
        System.out.printf("| %-2s | %-18s | %-10s | %-6s |\n", "ID", "NAME", "PRICE", "STOCK");
        System.out.println("=".repeat(60));
        menu.forEach(item ->
                System.out.printf("| %-2d | %-18s | ₹%-9.2f | %6d |\n",
                        item.getId(), item.getName(), item.getPrice(), item.getStock())
        );
        System.out.println("=".repeat(60));
    }

    private void viewDeliveryPartners() {
        System.out.println("\nDELIVERY PARTNERS STATUS");
        System.out.println("-".repeat(50));
        if (deliveryPartners.isEmpty()) {
            System.out.println("No delivery partners registered.");
        } else {
            deliveryPartners.forEach(dp -> System.out.println("  " + dp));
        }
        System.out.println("-".repeat(50));
    }

    // 💸 Allow admin to configure discount policy
    private void configureDiscount() {
        System.out.println("\n CURRENT DISCOUNT POLICY:");
        System.out.printf(" Get %.1f%% off if order total ≥ ₹%.2f\n", discountPercent, discountThreshold);

        System.out.print("Enter new minimum amount for discount (₹): ");
        try {
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                double threshold = Double.parseDouble(input);
                setDiscountThreshold(threshold);
            }
        } catch (Exception e) {
            System.out.println("Invalid amount entered.");
        }

        System.out.print("Enter new discount percentage (e.g., 10): ");
        try {
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                double percent = Double.parseDouble(input);
                setDiscountPercent(percent);
            }
        } catch (Exception e) {
            System.out.println("Invalid percentage entered.");
        }
    }

    // 📊 View basic sales report
    private void viewSalesReport() {
        if (orders.isEmpty()) {
            System.out.println("No orders placed yet.");
            return;
        }

        System.out.println("\n SALES REPORT");
        System.out.println("=".repeat(50));
        System.out.printf("%-10s : %d\n", "Total Orders", orders.size());

        double totalRevenue = orders.stream()
                .mapToDouble(order -> {
                    double subtotal = order.getCart().getTotal();
                    double discount = subtotal * (order.getDiscountPercent() / 100);
                    double gst = (subtotal - discount) * 0.10;
                    return subtotal - discount + gst;
                })
                .sum();

        System.out.printf("%-10s : ₹%.2f\n", "Total Revenue", totalRevenue);

        long codCount = orders.stream().filter(o -> "Cash".equals(o.getPaymentMethod())).count();
        long upiCount = orders.size() - codCount;

        System.out.printf("%-10s : %d (UPI), %d (Cash)\n", "Payments", upiCount, codCount);
        System.out.println("=".repeat(50));
    }

    private FoodItem findFoodById(int id) {
        return menu.stream()
                .filter(f -> f.getId() == id)
                .findFirst()
                .orElse(null);
    }
}