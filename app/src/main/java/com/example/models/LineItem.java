// com.example.model.LineItem.java
package com.example.models;

public class LineItem {
    private int orderId;
    private int productId;
    private int variantId;
    private int quantity;
    private double price; // REAL
    private double discountAmount; // REAL

    public LineItem() {}

    public LineItem(int orderId, int productId, int variantId, int quantity, double price, double discountAmount) {
        this.orderId = orderId;
        this.productId = productId;
        this.variantId = variantId;
        this.quantity = quantity;
        this.price = price;
        this.discountAmount = discountAmount;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public int getVariantId() { return variantId; }
    public void setVariantId(int variantId) { this.variantId = variantId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public double getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(double discountAmount) { this.discountAmount = discountAmount; }
}