package com.example.midsemmad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class AccountModel {
    SQLiteDatabase db;

    public void load(Context context){
        this.db = new DBHelper(context).getWritableDatabase();
    }

    public void addAccount(Account account){
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.AccountTable.Cols.ID, account.id);
        cv.put(DBSchema.AccountTable.Cols.NAME, account.name);
        cv.put(DBSchema.AccountTable.Cols.PASSWORD, account.password);
        db.insert(DBSchema.AccountTable.NAME, null, cv);
    }

    public ArrayList<Account> getAllAccount(){
        ArrayList<Account> checkoutList = new ArrayList<>();
        Cursor cursor = db.query(DBSchema.AccountTable.NAME,null,null,null,null,null,null);
        DBCursor dbCursor = new DBCursor(cursor);

        try{
            dbCursor.moveToFirst();
            while(!dbCursor.isAfterLast()){
                checkoutList.add(dbCursor.getAccount());
                dbCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return checkoutList;
    }
    public void deleteTable()
    {
        db.delete(DBSchema.AccountTable.NAME, null, null);
    }
}
