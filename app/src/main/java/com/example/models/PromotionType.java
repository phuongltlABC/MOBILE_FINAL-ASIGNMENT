package com.example.models;

public class PromotionType {
    private int promotionTypeId;
    private String typeName;
    private String description;
    private int isActive;

    public PromotionType(int promotionTypeId, String typeName, String description, int isActive) {
        this.promotionTypeId = promotionTypeId;
        this.typeName = typeName;
        this.description = description;
        this.isActive = isActive;
    }

    // Getters
    public int getPromotionTypeId() { return promotionTypeId; }
    public String getTypeName() { return typeName; }
    public String getDescription() { return description; }
    public int getIsActive() { return isActive; }

    // Setters
    public void setPromotionTypeId(int promotionTypeId) { this.promotionTypeId = promotionTypeId; }
    public void setTypeName(String typeName) { this.typeName = typeName; }
    public void setDescription(String description) { this.description = description; }
    public void setIsActive(int isActive) { this.isActive = isActive; }
}