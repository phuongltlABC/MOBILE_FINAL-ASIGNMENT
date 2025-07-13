// com.example.model.PaymentMethod.java
package com.example.models;

public class PaymentMethod {
    private int paymentId;
    private String paymentType;
    private String paymentName;
    private String description;
    private String paymentStatus; // e.g., "active", "inactive"
    private String currency;
    private int transactionFee; // According to your schema, it's INTEGER

    public PaymentMethod() {}

    public PaymentMethod(int paymentId, String paymentType, String paymentName, String description, String paymentStatus, String currency, int transactionFee) {
        this.paymentId = paymentId;
        this.paymentType = paymentType;
        this.paymentName = paymentName;
        this.description = description;
        this.paymentStatus = paymentStatus;
        this.currency = currency;
        this.transactionFee = transactionFee;
    }

    // Getters
    public int getPaymentId() { return paymentId; }
    public String getPaymentType() { return paymentType; }
    public String getPaymentName() { return paymentName; }
    public String getDescription() { return description; }
    public String getPaymentStatus() { return paymentStatus; }
    public String getCurrency() { return currency; }
    public int getTransactionFee() { return transactionFee; }

    // Setters
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }
    public void setPaymentName(String paymentName) { this.paymentName = paymentName; }
    public void setDescription(String description) { this.description = description; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public void setCurrency(String currency) { this.currency = currency; }
    public void setTransactionFee(int transactionFee) { this.transactionFee = transactionFee; }
}