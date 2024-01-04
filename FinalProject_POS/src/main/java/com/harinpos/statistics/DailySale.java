package com.harinpos.statistics;

public class DailySale {
    private String productName;
    private int dailyQuantity;
    private double dailySales;

    public DailySale(String productName, int dailyQuantity, double dailySales) {
        this.productName = productName;
        this.dailyQuantity = dailyQuantity;
        this.dailySales = dailySales;
    }

    public DailySale() {
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getDailyQuantity() {
        return dailyQuantity;
    }

    public void setDailyQuantity(int dailyQuantity) {
        this.dailyQuantity = dailyQuantity;
    }

    public double getDailySales() {
        return dailySales;
    }

    public void setDailySales(double dailySales) {
        this.dailySales = dailySales;
    }
}