// com.example.model.Order.java
package com.example.models;

public class Order {
    private int orderId;
    private String status;
    private int customerId;
    private String orderDate;
    private String deliveryDate;
    private String paymentDate;
    private int paymentId;
    private int totalPrice; // Theo bảng là INTEGER, nếu cần số thập phân thì dùng double/float
    private int promotionId;
    private int VAT;
    private int addressId;
    private int shipmentId;

    public Order() {}

    public Order(int orderId, String status, int customerId, String orderDate, String deliveryDate, String paymentDate, int paymentId, int totalPrice, int promotionId, int VAT, int addressId, int shipmentId) {
        this.orderId = orderId;
        this.status = status;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.paymentDate = paymentDate;
        this.paymentId = paymentId;
        this.totalPrice = totalPrice;
        this.promotionId = promotionId;
        this.VAT = VAT;
        this.addressId = addressId;
        this.shipmentId = shipmentId;
    }

    // Getters
    public int getOrderId() { return orderId; }
    public String getStatus() { return status; }
    public int getCustomerId() { return customerId; }
    public String getOrderDate() { return orderDate; }
    public String getDeliveryDate() { return deliveryDate; }
    public String getPaymentDate() { return paymentDate; }
    public int getPaymentId() { return paymentId; }
    public int getTotalPrice() { return totalPrice; }
    public int getPromotionId() { return promotionId; }
    public int getVAT() { return VAT; }
    public int getAddressId() { return addressId; }
    public int getShipmentId() { return shipmentId; }

    // Setters
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setStatus(String status) { this.status = status; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }
    public void setDeliveryDate(String deliveryDate) { this.deliveryDate = deliveryDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }
    public void setPromotionId(int promotionId) { this.promotionId = promotionId; }
    public void setVAT(int VAT) { this.VAT = VAT; }
    public void setAddressId(int addressId) { this.addressId = addressId; }
    public void setShipmentId(int shipmentId) { this.shipmentId = shipmentId; }
}