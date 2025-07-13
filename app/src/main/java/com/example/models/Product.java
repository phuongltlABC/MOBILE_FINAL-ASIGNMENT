package com.example.models; // Đảm bảo tên package này khớp với cấu trúc thư mục của bạn

import java.io.Serializable;

public class Product implements Serializable {
    private int productId;
    private String productStatus;
    private String productName;
    private int brandId;
    private int categoryId;
    private int roomId; // Giả định có mối liên hệ với Room
    private double price;
    private String description;
    private byte[] image; // Dữ liệu hình ảnh dưới dạng byte array (BLOB từ database)

    // Constructor mặc định (cần thiết cho một số thao tác của Firebase hoặc ORM,
    // hoặc khi bạn muốn tạo đối tượng rỗng và set từng thuộc tính sau)
    public Product() {}

    // Constructor đầy đủ với tất cả các thuộc tính
    public Product(int productId, String productStatus, String productName,
                   int brandId, int categoryId, int roomId,
                   double price, String description, byte[] image) {
        this.productId = productId;
        this.productStatus = productStatus;
        this.productName = productName;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.roomId = roomId;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    // --- Getters ---
    public int getProductId() {
        return productId;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public String getProductName() {
        return productName;
    }

    public int getBrandId() {
        return brandId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getRoomId() { // Getter cho RoomId
        return roomId;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getImage() { // Getter cho dữ liệu ảnh
        return image;
    }

    // --- Setters ---
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setRoomId(int roomId) { // Setter cho RoomId
        this.roomId = roomId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(byte[] image) { // Setter cho dữ liệu ảnh
        this.image = image;
    }

    // Tùy chọn: Ghi đè phương thức toString() để dễ dàng debug
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productStatus='" + productStatus + '\'' +
                ", productName='" + productName + '\'' +
                ", brandId=" + brandId +
                ", categoryId=" + categoryId +
                ", roomId=" + roomId +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", image_byte_array_size=" + (image != null ? image.length : "0") + // Không in trực tiếp byte array
                '}';
    }
}