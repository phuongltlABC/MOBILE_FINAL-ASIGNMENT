// com.example.model.Brand.java
package com.example.models;

public class Brand {
    private int brandId;
    private String brandName;
    private String description;

    public Brand(int brandId, String brandName, String description) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.description = description;
    }

    public int getBrandId() {
        return brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getDescription() {
        return description;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
