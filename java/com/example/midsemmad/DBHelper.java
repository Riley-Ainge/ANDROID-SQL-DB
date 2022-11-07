package com.example.midsemmad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 3;
    private static final String DATABASE_NAME = "menu.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ DBSchema.MenuTable.NAME+"("+ DBSchema.MenuTable.Cols.ID+" Text, "+ DBSchema.MenuTable.Cols.NAME+ " Text, "+ DBSchema.MenuTable.Cols.DESC+ " Text, "+ DBSchema.MenuTable.Cols.PRICE+ " Text, "+ DBSchema.MenuTable.Cols.LOCATION+ " Text, "+ DBSchema.MenuTable.Cols.RESTAURANT+ " Text);");
        sqLiteDatabase.execSQL("create table "+ DBSchema.RestaurantTable.NAME+"("+ DBSchema.RestaurantTable.Cols.NAME+" Text, "+ DBSchema.RestaurantTable.Cols.LOCATION+ " Text);");
        sqLiteDatabase.execSQL("create table "+ DBSchema.CheckoutTable.NAME+"("+ DBSchema.CheckoutTable.Cols.ID+" Text, "+ DBSchema.CheckoutTable.Cols.QUANTITY+ " Text, "+DBSchema.CheckoutTable.Cols.MENUID+" Text);");
        sqLiteDatabase.execSQL("create table "+ DBSchema.AccountTable.NAME+"("+ DBSchema.AccountTable.Cols.ID+" Text, "+ DBSchema.AccountTable.Cols.NAME+ " Text, "+DBSchema.AccountTable.Cols.PASSWORD+" Text);");
        sqLiteDatabase.execSQL("create table "+ DBSchema.LogTable.NAME+"("+ DBSchema.LogTable.Cols.ID+" Text, "+ DBSchema.LogTable.Cols.RESTAURANTS+ " Text, "+DBSchema.LogTable.Cols.PRICE+" Text, "+ DBSchema.LogTable.Cols.TOTALPRICE+" Text, "+ DBSchema.LogTable.Cols.ACCOUNTID+" Text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
