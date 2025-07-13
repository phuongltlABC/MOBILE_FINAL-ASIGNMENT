package com.example.models;

import java.io.Serializable;

public class CartItem implements Serializable {
    private int cartId;
    private int productId;
    private int variantId;
    private int quantity;
    private double price;
    private boolean isSelected = true;

    // Constructor mặc định
    public CartItem() {}

    // Constructor đầy đủ
    public CartItem(int cartId, int productId, int variantId, int quantity, double price) {
        this.cartId = cartId;
        this.productId = productId;
        this.variantId = variantId;
        this.quantity = quantity;
        this.price = price;
        this.isSelected = true; // mặc định chọn
    }

    // --- Getters ---
    public int getCartId() { return cartId; }
    public int getProductId() { return productId; }
    public int getVariantId() { return variantId; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public boolean isSelected() { return isSelected; }

    // --- Setters ---
    public void setCartId(int cartId) { this.cartId = cartId; }
    public void setProductId(int productId) { this.productId = productId; }
    public void setVariantId(int variantId) { this.variantId = variantId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }
    public void setSelected(boolean selected) { isSelected = selected; }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartId=" + cartId +
                ", productId=" + productId +
                ", variantId=" + variantId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", isSelected=" + isSelected +
                '}';
    }
}
