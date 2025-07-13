package com.example.models;

import android.graphics.Bitmap;

public class Color {
    private int colorCode;         // Mã màu (dạng số, ví dụ: 0xFFFFFF)
    private String colorName;      // Tên màu (Red, Blue,...)
    private byte[] imageBlob;      // Ảnh màu (dạng blob)
    private Bitmap imageBitmap;    // Nếu có bitmap riêng

    public Color(int colorCode, String colorName, byte[] imageBlob) {
        this.colorCode = colorCode;
        this.colorName = colorName;
        this.imageBlob = imageBlob;
    }

    // Getters
    public int getColorCode() {
        return colorCode;
    }

    public String getColorName() {
        return colorName;
    }

    public byte[] getImageBlob() {
        return imageBlob;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    // Setters
    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public void setImageBlob(byte[] imageBlob) {
        this.imageBlob = imageBlob;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }
}
