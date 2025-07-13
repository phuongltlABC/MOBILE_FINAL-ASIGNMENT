// com.example.model.ShipmentMethod.java
package com.example.models;

public class ShipmentMethod {
    private int shipmentId;
    private String shipmentName;
    private String description;
    private String contactNumber;
    private String email;
    private double deliveryFee;
    private int deliveryStatus; // 0 or 1
    private double deliveryTime;

    public ShipmentMethod() {}

    public ShipmentMethod(int shipmentId, String shipmentName, String description, String contactNumber, String email, double deliveryFee, int deliveryStatus, double deliveryTime) {
        this.shipmentId = shipmentId;
        this.shipmentName = shipmentName;
        this.description = description;
        this.contactNumber = contactNumber;
        this.email = email;
        this.deliveryFee = deliveryFee;
        this.deliveryStatus = deliveryStatus;
        this.deliveryTime = deliveryTime;
    }

    public int getShipmentId() { return shipmentId; }
    public void setShipmentId(int shipmentId) { this.shipmentId = shipmentId; }
    public String getShipmentName() { return shipmentName; }
    public void setShipmentName(String shipmentName) { this.shipmentName = shipmentName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public double getDeliveryFee() { return deliveryFee; }
    public void setDeliveryFee(double deliveryFee) { this.deliveryFee = deliveryFee; }
    public int getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(int deliveryStatus) { this.deliveryStatus = deliveryStatus; }
    public double getDeliveryTime() { return deliveryTime; }
    public void setDeliveryTime(double deliveryTime) { this.deliveryTime = deliveryTime; }
}