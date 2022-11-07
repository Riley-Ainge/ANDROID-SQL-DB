package com.example.midsemmad;

public class Log {
    public String id;
    public String restaurants;
    public String price;
    public String totalPrice;
    public String accountID;

    public Log(String id, String restaurants, String price, String totalPrice, String accountID) {
        this.id = id;
        this.restaurants = restaurants;
        this.price = price;
        this.accountID = accountID;
        this.totalPrice = totalPrice;
    }
}
