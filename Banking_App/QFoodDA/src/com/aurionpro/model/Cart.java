package com.aurionpro.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<FoodItem, Integer> items; // FoodItem -> quantity

    public Cart() {
        items = new HashMap<>();
    }

    /**
     * Adds a food item to the cart. If already present, increases quantity.
     * Throws exception if stock is insufficient.
     */
    public void addItem(FoodItem food, int quantity) throws CustomException.OutOfStockException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        if (food.getStock() < quantity) {
            throw new CustomException.OutOfStockException(
                "Only " + food.getStock() + " units of '" + food.getName() + "' available."
            );
        }

        int currentQty = items.getOrDefault(food, 0);
        int totalNeeded = currentQty + quantity;

        if (totalNeeded > food.getStock()) {
            throw new CustomException.OutOfStockException(
                "Cannot add: total requested (" + totalNeeded + ") exceeds stock (" + food.getStock() + ")."
            );
        }

        items.put(food, totalNeeded);
    }

    /**
     * Removes an item from the cart
     */
    public void removeItem(FoodItem food) {
        items.remove(food);
    }

    /**
     * Checks if the cart contains the given food item
     */
    public boolean contains(FoodItem food) {
        return items.containsKey(food);
    }

    /**
     * Returns a copy of items in cart (safe export)
     */
    public Map<FoodItem, Integer> getItems() {
        return new HashMap<>(items);
    }

    /**
     * Calculates total price of all items in cart
     */
    public double getTotal() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

   
    public double getTotalAfterDiscount(double discountPercent) {
        if (discountPercent < 0) discountPercent = 0;
        if (discountPercent > 100) discountPercent = 100;

        double total = getTotal();
        return total - (total * discountPercent / 100);
    }

   
    public boolean isEmpty() {
        return items.isEmpty();
    }

   
    public void clear() {
        items.clear();
    }

   
    
    public int getQuantity(FoodItem food) {
        return items.getOrDefault(food, 0);
    }
}