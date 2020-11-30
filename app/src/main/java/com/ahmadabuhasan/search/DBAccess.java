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
    private SQLiteOpenHelper openHelper;

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

    public ArrayList<HashMap<String, String>> getSearchBarang(String s) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        SQLiteDatabase sQLiteDatabase = this.database;
        Cursor cursor = sQLiteDatabase.rawQuery("SELECT * FROM barang WHERE NamaBarang LIKE '%" + s + "%' OR Harga LIKE '%" + s + "%' ORDER BY id DESC", (String[]) null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put(DBHelper.BARANG_ID, cursor.getString(0));
                map.put(DBHelper.BARANG_NAMA, cursor.getString(1));
                map.put(DBHelper.BARANG_BELI, cursor.getString(2));
                map.put(DBHelper.BARANG_STOCK, cursor.getString(3));
                map.put(DBHelper.BARANG_HARGA, cursor.getString(4));
                product.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.database.close();
        return product;
    }

}
