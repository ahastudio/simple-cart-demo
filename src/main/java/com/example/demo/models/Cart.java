package com.example.demo.models;

public class Cart {
    private String id;
    private String userId;
    private int totalPrice;

    public Cart(String id, String userId, int totalPrice) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
