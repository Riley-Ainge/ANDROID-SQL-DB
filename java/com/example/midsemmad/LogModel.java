package com.example.midsemmad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class LogModel {
    SQLiteDatabase db;

    public void load(Context context){
        this.db = new DBHelper(context).getWritableDatabase();
    }

    public void addLog(Log log){
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.LogTable.Cols.ID, log.id);
        cv.put(DBSchema.LogTable.Cols.RESTAURANTS, log.restaurants);
        cv.put(DBSchema.LogTable.Cols.PRICE, log.price);
        cv.put(DBSchema.LogTable.Cols.TOTALPRICE, log.totalPrice);
        cv.put(DBSchema.LogTable.Cols.ACCOUNTID, log.accountID);
        db.insert(DBSchema.LogTable.NAME, null, cv);
    }

    public ArrayList<Log> getAllLog(){
        ArrayList<Log> logList = new ArrayList<>();
        Cursor cursor = db.query(DBSchema.LogTable.NAME,null,null,null,null,null,null);
        DBCursor dbCursor = new DBCursor(cursor);

        try{
            dbCursor.moveToFirst();
            while(!dbCursor.isAfterLast()){
                logList.add(dbCursor.getLog());
                dbCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return logList;
    }
    public ArrayList<Log> getLogForAccount(String accountID){
        ArrayList<Log> logList = new ArrayList<>();
        Cursor cursor = db.query(DBSchema.LogTable.NAME,null,DBSchema.LogTable.Cols.ACCOUNTID+"="+"'"+accountID+"'",null,null,null,null);
        DBCursor dbCursor = new DBCursor(cursor);

        try{
            dbCursor.moveToFirst();
            while(!dbCursor.isAfterLast()){
                logList.add(dbCursor.getLog());
                dbCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return logList;
    }
    public void deleteTable()
    {
        db.delete(DBSchema.LogTable.NAME, null, null);
    }
}
