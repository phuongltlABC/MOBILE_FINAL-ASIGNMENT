package com.example.models;

public class Promotion {
    private int promotionId;
    private String promotionName;
    private String description;
    private String startDate;
    private String endDate;
    private String discountType;
    private int discountValue;
    private int maxDiscount;
    private int minOrderValue;
    private int promotionTypeId;
    private int isActive;

    public Promotion(int promotionId, String promotionName, String description, String startDate,
                     String endDate, String discountType, int discountValue, int maxDiscount,
                     int minOrderValue, int promotionTypeId, int isActive) {
        this.promotionId = promotionId;
        this.promotionName = promotionName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.maxDiscount = maxDiscount;
        this.minOrderValue = minOrderValue;
        this.promotionTypeId = promotionTypeId;
        this.isActive = isActive;
    }

    // Getters
    public int getPromotionId() { return promotionId; }
    public String getPromotionName() { return promotionName; }
    public String getDescription() { return description; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getDiscountType() { return discountType; }
    public int getDiscountValue() { return discountValue; }
    public int getMaxDiscount() { return maxDiscount; }
    public int getMinOrderValue() { return minOrderValue; }
    public int getPromotionTypeId() { return promotionTypeId; }
    public int getIsActive() { return isActive; }

    // Setters
    public void setPromotionId(int promotionId) { this.promotionId = promotionId; }
    public void setPromotionName(String promotionName) { this.promotionName = promotionName; }
    public void setDescription(String description) { this.description = description; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public void setDiscountType(String discountType) { this.discountType = discountType; }
    public void setDiscountValue(int discountValue) { this.discountValue = discountValue; }
    public void setMaxDiscount(int maxDiscount) { this.maxDiscount = maxDiscount; }
    public void setMinOrderValue(int minOrderValue) { this.minOrderValue = minOrderValue; }
    public void setPromotionTypeId(int promotionTypeId) { this.promotionTypeId = promotionTypeId; }
    public void setIsActive(int isActive) { this.isActive = isActive; }
}