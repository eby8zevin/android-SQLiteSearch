package com.ahmadabuhasan.search;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ahmad Abu Hasan on 30/11/2020
 */

public class DBAccess {

    private static DBAccess instance;
    private SQLiteDatabase database;
    private final SQLiteOpenHelper openHelper;

    private DBAccess(Context context) {
        this.openHelper = new DBHelper(context);
    }

    public static DBAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DBAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = this.openHelper.getWritableDatabase();
    }

    public void close() {
        SQLiteDatabase sQLiteDatabase = this.database;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }

    public ArrayList<HashMap<String, String>> getSearchData(String s) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        SQLiteDatabase sQLiteDatabase = this.database;
        Cursor cursor = sQLiteDatabase.rawQuery("SELECT * FROM data WHERE NameProduct LIKE '%" + s + "%' OR Price LIKE '%" + s + "%' ORDER BY DataId DESC", (String[]) null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put(DBHelper.DATA_ID, cursor.getString(0));
                map.put(DBHelper.DATA_NAME, cursor.getString(1));
                map.put(DBHelper.DATA_BUY, cursor.getString(2));
                map.put(DBHelper.DATA_STOCK, cursor.getString(3));
                map.put(DBHelper.DATA_PRICE, cursor.getString(4));
                product.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return product;
    }

    public ArrayList<HashMap<String, String>> getData() {
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        Cursor cursor = this.database.rawQuery("SELECT * FROM data ORDER BY DataId ASC", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put(DBHelper.DATA_ID, cursor.getString(0));
                map.put(DBHelper.DATA_NAME, cursor.getString(1));
                map.put(DBHelper.DATA_BUY, cursor.getString(2));
                map.put(DBHelper.DATA_STOCK, cursor.getString(3));
                map.put(DBHelper.DATA_PRICE, cursor.getString(4));
                data.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return data;
    }
}