// com.example.model.AddressInfo.java
package com.example.models;

public class AddressInfo {
    private int addressId;
    private int customerId;
    private int isDefault; // 0 or 1
    private String deliveryName;
    private String contactNumber;
    private String email;
    private String address;
    private int deliveryFee;

    public AddressInfo() {}

    public AddressInfo(int addressId, int customerId, int isDefault, String deliveryName, String contactNumber, String email, String address, int deliveryFee) {
        this.addressId = addressId;
        this.customerId = customerId;
        this.isDefault = isDefault;
        this.deliveryName = deliveryName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
        this.deliveryFee = deliveryFee;
    }

    public int getAddressId() { return addressId; }
    public void setAddressId(int addressId) { this.addressId = addressId; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public int getIsDefault() { return isDefault; }
    public void setIsDefault(int isDefault) { this.isDefault = isDefault; }
    public String getDeliveryName() { return deliveryName; }
    public void setDeliveryName(String deliveryName) { this.deliveryName = deliveryName; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public int getDeliveryFee() { return deliveryFee; }
    public void setDeliveryFee(int deliveryFee) { this.deliveryFee = deliveryFee; }
}