// com.example.model.Account.java
package com.example.models;

public class Account {
    private int accountId;
    private int isAnonymous; // 0 or 1
    private int customerId;
    private String username;
    private String password;
    private int points;
    private String status;
    private String rank;

    public Account() {}

    public Account(int accountId, int isAnonymous, int customerId, String username, String password, int points, String status, String rank) {
        this.accountId = accountId;
        this.isAnonymous = isAnonymous;
        this.customerId = customerId;
        this.username = username;
        this.password = password;
        this.points = points;
        this.status = status;
        this.rank = rank;
    }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }
    public int getIsAnonymous() { return isAnonymous; }
    public void setIsAnonymous(int isAnonymous) { this.isAnonymous = isAnonymous; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }


}