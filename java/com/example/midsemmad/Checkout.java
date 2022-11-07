package com.example.midsemmad;

public class Checkout {
    public String id;
    public String quantity;
    public String menuItemID;

    public Checkout(String id, String quantity, String menuItemID) {
        this.id = id;
        this.quantity = quantity;
        this.menuItemID = menuItemID;
    }
}
