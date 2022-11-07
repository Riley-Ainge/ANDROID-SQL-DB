package com.example.midsemmad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class RestaurantModel {
    SQLiteDatabase db;

    public void load(Context context){
        this.db = new DBHelper(context).getWritableDatabase();
    }

    public void addRestaurant(Restaurant restaurant){
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.RestaurantTable.Cols.NAME, restaurant.name);
        cv.put(DBSchema.RestaurantTable.Cols.LOCATION, restaurant.pictureLocation);
        db.insert(DBSchema.RestaurantTable.NAME, null, cv);
    }

    public ArrayList<Restaurant> getAllRestaurant(){
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        Cursor cursor = db.query(DBSchema.RestaurantTable.NAME,null,null,null,null,null,null);
        DBCursor dbCursor = new DBCursor(cursor);

        try{
            dbCursor.moveToFirst();
            while(!dbCursor.isAfterLast()){
                restaurantList.add(dbCursor.getRestaurant());
                dbCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return restaurantList;
    }
    public void deleteTable()
    {
        db.delete(DBSchema.RestaurantTable.NAME, null, null);
    }
}
