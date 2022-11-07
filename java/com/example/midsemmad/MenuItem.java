package com.example.midsemmad;

public class MenuItem {
    public String id;
    public String name;
    public String description;
    public String price;
    public String pictureLocation;
    public String restaurantName;

    public MenuItem(String id, String name, String description, String price, String pictureLocation, String restaurantName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictureLocation = pictureLocation;
        this.restaurantName = restaurantName;

    }
}
