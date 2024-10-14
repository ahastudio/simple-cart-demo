package com.example.demo.models;

public class LineItem {
    private String id;
    private String cartId;
    private String productId;
    private int unitPrice;
    private int quantity;
    private int totalPrice;

    public LineItem(
            String cartId, String productId, int unitPrice, int quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = unitPrice * quantity;
    }

    public LineItem(
            String id, String cartId, String productId,
            int unitPrice, int quantity, int totalPrice) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public String getCartId() {
        return cartId;
    }

    public String getProductId() {
        return productId;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
