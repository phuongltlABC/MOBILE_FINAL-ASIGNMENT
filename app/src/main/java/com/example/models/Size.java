package com.example.models;

public class Size {
    private int sizeCode;
    private String sizeName;

    public Size(int sizeCode, String sizeName) {
        this.sizeCode = sizeCode;
        this.sizeName = sizeName;
    }

    public int getSizeCode() { return sizeCode; }
    public String getSizeName() { return sizeName; }

    public void setSizeCode(int sizeCode) { this.sizeCode = sizeCode; }
    public void setSizeName(String sizeName) { this.sizeName = sizeName; }
}