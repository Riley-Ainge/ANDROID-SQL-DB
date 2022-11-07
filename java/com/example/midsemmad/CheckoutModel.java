package com.example.midsemmad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class CheckoutModel {
    SQLiteDatabase db;

    public void load(Context context){
        this.db = new DBHelper(context).getWritableDatabase();
    }

    public void addCheckout(Checkout checkout){
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.CheckoutTable.Cols.ID, checkout.id);
        cv.put(DBSchema.CheckoutTable.Cols.QUANTITY, checkout.quantity);
        cv.put(DBSchema.CheckoutTable.Cols.MENUID, checkout.menuItemID);
        db.insert(DBSchema.CheckoutTable.NAME, null, cv);
    }

    public ArrayList<Checkout> getAllCheckout(){
        ArrayList<Checkout> checkoutList = new ArrayList<>();
        Cursor cursor = db.query(DBSchema.CheckoutTable.NAME,null,null,null,null,null,null);
        DBCursor dbCursor = new DBCursor(cursor);

        try{
            dbCursor.moveToFirst();
            while(!dbCursor.isAfterLast()){
                checkoutList.add(dbCursor.getCheckout());
                dbCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return checkoutList;
    }
    public ArrayList<Checkout> getCheckoutForMenuItem(String menuItemID){
        ArrayList<Checkout> checkoutList = new ArrayList<>();
        Cursor cursor = db.query(DBSchema.CheckoutTable.NAME,null,DBSchema.CheckoutTable.Cols.MENUID+"="+"'"+menuItemID+"'",null,null,null,null);
        DBCursor dbCursor = new DBCursor(cursor);

        try{
            dbCursor.moveToFirst();
            while(!dbCursor.isAfterLast()){
                checkoutList.add(dbCursor.getCheckout());
                dbCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return checkoutList;
    }
    public ArrayList<Checkout> getCheckoutForID(String ID){
        ArrayList<Checkout> checkoutList = new ArrayList<>();
        Cursor cursor = db.query(DBSchema.CheckoutTable.NAME,null,DBSchema.CheckoutTable.Cols.ID+"="+"'"+ID+"'",null,null,null,null);
        DBCursor dbCursor = new DBCursor(cursor);

        try{
            dbCursor.moveToFirst();
            while(!dbCursor.isAfterLast()){
                checkoutList.add(dbCursor.getCheckout());
                dbCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return checkoutList;
    }
    public void removeCheckout(Checkout checkout)
    {
        String[] whereValue = { String.valueOf(checkout.id) };
        db.delete(DBSchema.CheckoutTable.NAME,
                DBSchema.CheckoutTable.Cols.ID + " = ?", whereValue);
    }
    public void updateCheckout(Checkout checkout, String newQuantity)
    {
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.CheckoutTable.Cols.ID, checkout.id);
        cv.put(DBSchema.CheckoutTable.Cols.QUANTITY, newQuantity);
        cv.put(DBSchema.CheckoutTable.Cols.MENUID, checkout.menuItemID);
        String[] whereValue = { String.valueOf(checkout.id) };
        db.update(DBSchema.CheckoutTable.NAME, cv,
                DBSchema.CheckoutTable.Cols.ID + " = ?", whereValue);
    }
    public void deleteTable()
    {
        db.delete(DBSchema.CheckoutTable.NAME, null, null);
    }
}
