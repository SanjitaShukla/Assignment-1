package com.aurionpro.model;

public class DeliveryPartner {
    private int id;
    private String name;
    private String vehicleNumber; // e.g., "DL4 ABC123" – useful for customer
    private boolean isAssigned;

    public DeliveryPartner(int id, String name, String vehicleNumber) {
        this.id = id;
        this.name = name;
        this.vehicleNumber = vehicleNumber;
        this.isAssigned = false;
    }

    // --- Getters ---
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    // --- Setters ---
    public void setAssigned(boolean assigned) {
        this.isAssigned = assigned;
    }

    
    public void assign() {
        this.setAssigned(true);
    }

    public void free() {
        this.setAssigned(false);
    }

    @Override
    public String toString() {
        return "DP" + id + " - " + name + " | Vehicle: " + vehicleNumber + " | " + (isAssigned ? "Busy" : "Free");
    }
}