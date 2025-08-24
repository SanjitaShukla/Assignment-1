package com.bankapp.model;

public class Transaction {
    private int transactionId;
    private int fromAccountId;
    private int toAccountId;
    private double amount;
    private String type;
    private String timestamp;
    private String fromAccountNum;
    private String toAccountNum;

    // Getters and Setters
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }

    public int getFromAccountId() { return fromAccountId; }
    public void setFromAccountId(int fromAccountId) { this.fromAccountId = fromAccountId; }

    public int getToAccountId() { return toAccountId; }
    public void setToAccountId(int toAccountId) { this.toAccountId = toAccountId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getFromAccountNum() { return fromAccountNum; }
    public void setFromAccountNum(String fromAccountNum) { this.fromAccountNum = fromAccountNum; }

    public String getToAccountNum() { return toAccountNum; }
    public void setToAccountNum(String toAccountNum) { this.toAccountNum = toAccountNum; }
}