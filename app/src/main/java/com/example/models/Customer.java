// com.example.model.Customer.java
package com.example.models;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int credit;
    private byte[] avatar; // BLOB for image

    public Customer() {}

    public Customer(int customerId, String firstName, String lastName, String email, String phoneNumber, int credit, byte[] avatar) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.credit = credit;
        this.avatar = avatar;
    }

    // Getters
    public int getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public int getCredit() { return credit; }
    public byte[] getAvatar() {
        return avatar != null ? avatar.clone() : null; // Return a defensive copy
    }

    // Setters
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setCredit(int credit) { this.credit = credit; }
    public void setAvatar(byte[] avatar) {
        this.avatar = avatar != null ? avatar.clone() : null; // Store a defensive copy
    }
}