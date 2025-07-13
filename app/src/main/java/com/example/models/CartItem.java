package com.example.models;

public class CartItem {
    private int cartId;
    private int productId;
    private int variantId; // <-- THÊM THUỘC TÍNH variantId VÀO ĐÂY
    private int quantity;
    private double price; // <-- NÊN DÙNG double/float CHO GIÁ TIỀN, TRÁNH MẤT CHÍNH XÁC

    // Constructor mặc định (nếu bạn cần nó)
    public CartItem() {}

    // Constructor với tất cả các thuộc tính
    public CartItem(int cartId, int productId, int variantId, int quantity, double price) {
        this.cartId = cartId;
        this.productId = productId;
        this.variantId = variantId; // <-- KHỞI TẠO variantId TRONG CONSTRUCTOR
        this.quantity = quantity;
        this.price = price;
    }

    // --- Getters ---
    public int getCartId() { return cartId; }
    public int getProductId() { return productId; }
    public int getVariantId() { return variantId; } // <-- THÊM PHƯƠNG THỨC GETTER NÀY
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; } // <-- Đổi kiểu trả về thành double

    // --- Setters ---
    public void setCartId(int cartId) { this.cartId = cartId; }
    public void setProductId(int productId) { this.productId = productId; }
    public void setVariantId(int variantId) { this.variantId = variantId; } // <-- THÊM PHƯƠNG THỨC SETTER NÀY
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; } // <-- Đổi kiểu tham số thành double

    // Tùy chọn: Ghi đè phương thức toString() để dễ dàng debug
    @Override
    public String toString() {
        return "CartItem{" +
                "cartId=" + cartId +
                ", productId=" + productId +
                ", variantId=" + variantId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}