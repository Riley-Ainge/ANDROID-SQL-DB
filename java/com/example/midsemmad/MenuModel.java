package com.example.midsemmad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MenuModel {
    SQLiteDatabase db;

    public void load(Context context){
        this.db = new DBHelper(context).getWritableDatabase();
    }

    public void addMenu(MenuItem menu){
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.MenuTable.Cols.ID, menu.id);
        cv.put(DBSchema.MenuTable.Cols.NAME, menu.name);
        cv.put(DBSchema.MenuTable.Cols.DESC, menu.description);
        cv.put(DBSchema.MenuTable.Cols.PRICE, menu.price);
        cv.put(DBSchema.MenuTable.Cols.LOCATION, menu.pictureLocation);
        cv.put(DBSchema.MenuTable.Cols.RESTAURANT, menu.restaurantName);
        db.insert(DBSchema.MenuTable.NAME, null, cv);
    }

    public ArrayList<MenuItem> getAllMenu(){
        ArrayList<MenuItem> menuList = new ArrayList<>();
        Cursor cursor = db.query(DBSchema.MenuTable.NAME,null,null,null,null,null,null);
        DBCursor dbCursor = new DBCursor(cursor);

        try{
            dbCursor.moveToFirst();
            while(!dbCursor.isAfterLast()){
                menuList.add(dbCursor.getMenu());
                dbCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return menuList;
    }
    public ArrayList<MenuItem> getMenuForRestaurant(String restaurantName){
        ArrayList<MenuItem> menuList = new ArrayList<>();
        Cursor cursor = db.query(DBSchema.MenuTable.NAME,null,DBSchema.MenuTable.Cols.RESTAURANT+"="+"'"+restaurantName+"'",null,null,null,null);
        DBCursor dbCursor = new DBCursor(cursor);

        try{
            dbCursor.moveToFirst();
            while(!dbCursor.isAfterLast()){
                menuList.add(dbCursor.getMenu());
                dbCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return menuList;
    }
    public void deleteTable()
    {
        db.delete(DBSchema.MenuTable.NAME, null, null);
    }
}
