// com.example.model.Cart.java
package com.example.models;

public class Cart {
    private int cartId;
    private int accountId;
    private String createdAt;
    private String updatedAt;

    public Cart() {}

    public Cart(int cartId, int accountId, String createdAt, String updatedAt) {
        this.cartId = cartId;
        this.accountId = accountId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}