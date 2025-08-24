package com.aurionpro.model;
import java.util.*;

public class FoodDeliveryMain {
    public static void main(String[] args) {
        // Shared data across Admin and Customer
        List<FoodItem> menu = new ArrayList<>();
        List<DeliveryPartner> deliveryPartners = Arrays.asList(
            new DeliveryPartner(1, "Zomato", "DL1-AB2345"),
            new DeliveryPartner(2, "Swiggy", "DL2-XY6789"),
            new DeliveryPartner(3, "Zepto", "DL3-PQ0123")
        );
        List<Order> orders = new ArrayList<>();

        // Preload menu
        menu.add(new FoodItem(1, "Pizza", 299.00, 10));
        menu.add(new FoodItem(2, "Cake", 149.00, 15));
        menu.add(new FoodItem(3, "Pasta", 180.00, 8));
        menu.add(new FoodItem(4, "Mud Pie", 199.00, 5));
        menu.add(new FoodItem(5, "Salad", 99.00, 12));

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        System.out.println(" Welcome to Food Delivery App!");

        do {
            System.out.println("\n" + "=".repeat(45));
            System.out.println("               MAIN MENU");
            System.out.println("=".repeat(45));
            System.out.println("1. Admin Panel");
            System.out.println("2. Customer Mode");
            System.out.println("3. Exit");
            System.out.println("=".repeat(45));
            System.out.print("Select role: ");

            try {
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println(" Input cannot be empty.");
                    continue;
                }
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input! Please enter 1, 2, or 3.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("\n Accessing Admin Panel...");
                    Admin admin = new Admin(menu, deliveryPartners, orders);
                    admin.start();
                }
                case 2 -> {
                    System.out.println("\n Entering Customer Mode...");
                    Customer customer = new Customer(menu, deliveryPartners);
                    // Optional: Sync discount rules from saved settings later
                    customer.start();
                }
                case 3 -> System.out.println("Thank you for using Food Delivery App! Have a great day!");
                default -> System.out.println(" Invalid choice! Please select 1, 2, or 3.");
            }

        } while (choice != 3);

        sc.close(); 
    }
}