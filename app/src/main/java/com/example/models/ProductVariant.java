package com.example.models;

import android.graphics.Bitmap;

public class ProductVariant {
    private int variantId;
    private int productId;
    private int colorCode;
    private int sizeCode;
    private int stockQuantity;
    private double price;
    private byte[] imageBlob;
    private Bitmap imageBitmap;

    public ProductVariant(int variantId, int productId, int colorCode, int sizeCode, int stockQuantity, double price, byte[] imageBlob) {
        this.variantId = variantId;
        this.productId = productId;
        this.colorCode = colorCode;
        this.sizeCode = sizeCode;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.imageBlob = imageBlob;
    }

    // Getters
    public int getVariantId() { return variantId; }
    public int getProductId() { return productId; }
    public int getColorCode() { return colorCode; }
    public int getSizeCode() { return sizeCode; }
    public int getStockQuantity() { return stockQuantity; }
    public double getPrice() { return price; }
    public byte[] getImageBlob() { return imageBlob; }
    public Bitmap getImageBitmap() { return imageBitmap; }

    // Setters
    public void setVariantId(int variantId) { this.variantId = variantId; }
    public void setProductId(int productId) { this.productId = productId; }
    public void setColorCode(int colorCode) { this.colorCode = colorCode; }
    public void setSizeCode(int sizeCode) { this.sizeCode = sizeCode; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public void setPrice(double price) { this.price = price; }
    public void setImageBlob(byte[] imageBlob) { this.imageBlob = imageBlob; }
    public void setImageBitmap(Bitmap imageBitmap) { this.imageBitmap = imageBitmap; }
}