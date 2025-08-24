package com.aurionpro.model;

public class CustomException {

    public static class InvalidFoodException extends Exception {
        public InvalidFoodException(String message) {
            super(message);
        }
    }

    public static class OutOfStockException extends Exception {
        public OutOfStockException(String message) {  
        }
    }

    public static class DeliveryPartnerUnavailableException extends Exception {
        public DeliveryPartnerUnavailableException(String message) {
            super(message);
        }
    }
}