package com.example.midsemmad;

import android.database.Cursor;
import android.database.CursorWrapper;

public class DBCursor extends CursorWrapper {
    public DBCursor(Cursor cursor) {
        super(cursor);
    }

    public MenuItem getMenu(){
        String id = getString(getColumnIndex(DBSchema.MenuTable.Cols.ID));
        String name = getString(getColumnIndex(DBSchema.MenuTable.Cols.NAME));
        String desc = getString(getColumnIndex(DBSchema.MenuTable.Cols.DESC));
        String price = getString(getColumnIndex(DBSchema.MenuTable.Cols.PRICE));
        String location = getString(getColumnIndex(DBSchema.MenuTable.Cols.LOCATION));
        String restaurant = getString(getColumnIndex(DBSchema.MenuTable.Cols.RESTAURANT));
        return new MenuItem(id,name, desc, price, location, restaurant);
    }
    public Restaurant getRestaurant(){
        String name = getString(getColumnIndex(DBSchema.RestaurantTable.Cols.NAME));
        String location = getString(getColumnIndex(DBSchema.RestaurantTable.Cols.LOCATION));
        return new Restaurant(name, location);
    }
    public Checkout getCheckout(){
        String id = getString(getColumnIndex(DBSchema.CheckoutTable.Cols.ID));
        String quantity = getString(getColumnIndex(DBSchema.CheckoutTable.Cols.QUANTITY));
        String menuID = getString(getColumnIndex(DBSchema.CheckoutTable.Cols.MENUID));
        return new Checkout(id, quantity, menuID);
    }
    public Account getAccount(){
        String id = getString(getColumnIndex(DBSchema.AccountTable.Cols.ID));
        String name = getString(getColumnIndex(DBSchema.AccountTable.Cols.NAME));
        String password = getString(getColumnIndex(DBSchema.AccountTable.Cols.PASSWORD));
        return new Account(id, name, password);
    }
    public Log getLog(){
        String id = getString(getColumnIndex(DBSchema.LogTable.Cols.ID));
        String restaurants = getString(getColumnIndex(DBSchema.LogTable.Cols.RESTAURANTS));
        String price = getString(getColumnIndex(DBSchema.LogTable.Cols.PRICE));
        String totalPrice = getString(getColumnIndex(DBSchema.LogTable.Cols.TOTALPRICE));
        String accountID = getString(getColumnIndex(DBSchema.LogTable.Cols.ACCOUNTID));
        return new Log(id, restaurants, price, totalPrice, accountID);
    }
}
