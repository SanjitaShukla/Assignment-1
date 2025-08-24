package com.aurionpro.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private static int nextOrderId = 1;
    private int orderId;
    private Cart cart;
    private DeliveryPartner deliveryPartner;
    private LocalDateTime orderDate;
    private String status;
    private String paymentMethod; // "UPI" or "Cash"
    private double discountPercent; // e.g., 10.0 for 10% off
    private double appliedDiscountAmount;

    public Order(Cart cart, DeliveryPartner deliveryPartner, String paymentMethod, double discountPercent) {
        this.orderId = nextOrderId++;
        this.cart = new Cart();
        cart.getItems().forEach((item, qty) -> {
            try {
                this.cart.addItem(item, qty);
            } catch (CustomException.OutOfStockException e) {
                // Should not happen at this stage, but log just in case
                System.err.println("Warning: Stock issue during order creation for " + item.getName());
            }
        });

        this.deliveryPartner = deliveryPartner;
        this.paymentMethod = paymentMethod;
        this.discountPercent = discountPercent;
        this.appliedDiscountAmount = cart.getTotal() * (discountPercent / 100);
        this.orderDate = LocalDateTime.now();
        this.status = "CONFIRMED";

        this.deliveryPartner.setAssigned(true);
    }

    
    public Order(Cart cart, DeliveryPartner deliveryPartner, String paymentMethod) {
        this(cart, deliveryPartner, paymentMethod, 0.0);
    }

    public void printInvoice() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("                  ORDER INVOICE");
        System.out.println("=".repeat(60));
        System.out.printf("%-20s: %d\n", "Order ID", orderId);
        System.out.printf("%-20s: %s\n", "Date & Time", orderDate.format(formatter));
        System.out.printf("%-20s: %s\n", "Status", status);
        System.out.printf("%-20s: %s\n", "Payment Method", paymentMethod);
        System.out.println("-".repeat(60));

        System.out.printf("%-15s %-10s %-5s %-10s\n", "ITEM", "PRICE", "QTY", "TOTAL");
        System.out.println("-".repeat(60));

        cart.getItems().forEach((item, qty) ->
                System.out.printf("%-15s ₹%-9.2f x %-3d ₹%-10.2f\n",
                        item.getName(), item.getPrice(), qty, item.getPrice() * qty)
        );

        System.out.println("-".repeat(60));
        double subtotal = cart.getTotal();
        System.out.printf("%40s: ₹%.2f\n", "SUBTOTAL", subtotal);

        if (discountPercent > 0) {
            System.out.printf("%40s: -₹%.2f (%.1f%% off)\n", "DISCOUNT", appliedDiscountAmount, discountPercent);
        }

        double gstAmount = (subtotal - appliedDiscountAmount) * 0.10; // 10% GST on discounted value
        System.out.printf("%40s: ₹%.2f (10%%)\n", "GST", gstAmount);

        double finalTotal = subtotal - appliedDiscountAmount + gstAmount;
        System.out.printf("%40s: ₹%.2f\n", "GRAND TOTAL", finalTotal);

        System.out.println("-".repeat(60));
        System.out.printf(" DELIVERY BY: %s (Contact: %s)\n",
                          deliveryPartner.getName(), deliveryPartner.getVehicleNumber());
        System.out.println("=".repeat(60));
    }

    // --- Getters ---
    public int getOrderId() { return orderId; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public Cart getCart() { return cart; }
    public DeliveryPartner getDeliveryPartner() { return deliveryPartner; }
    public String getStatus() { return status; }
    public String getPaymentMethod() { return paymentMethod; }
    public double getDiscountPercent() { return discountPercent; }
    public double getAppliedDiscountAmount() { return appliedDiscountAmount; }
}
