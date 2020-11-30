package com.ahmadabuhasan.search;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ahmad Abu Hasan on 30/11/2020
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "skripsi.db";

    public static final String TABLE_BARANG = "barang";

    public static final String BARANG_ID = "id";
    public static final String BARANG_NAMA = "NamaBarang";
    public static final String BARANG_BELI = "Beli";
    public static final String BARANG_STOCK = "Stock";
    public static final String BARANG_HARGA = "Harga";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE_BARANG = "CREATE TABLE " + TABLE_BARANG +
            "(" + BARANG_ID + " INTEGER PRIMARY KEY,"
            + BARANG_NAMA + " TEXT,"
            + BARANG_BELI + " TEXT,"
            + BARANG_STOCK + " TEXT,"
            + BARANG_HARGA + " TEXT"
            + ")";

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_BARANG);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BARANG);

    }

    public void add(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(BARANG_NAMA, "Kuas");
        values.put(BARANG_BELI, "1000");
        values.put(BARANG_STOCK, "10");
        values.put(BARANG_HARGA, "2000");

        db.insert(TABLE_BARANG, BARANG_NAMA, values);
//        db.insert(TABLE_BARANG, BARANG_BELI, values);
//        db.insert(TABLE_BARANG, BARANG_STOCK, values);
//        db.insert(TABLE_BARANG, BARANG_HARGA, values);
//
        values.put(BARANG_NAMA, "Paku");
        values.put(BARANG_BELI, "3000");
        values.put(BARANG_STOCK, "20");
        values.put(BARANG_HARGA, "4000");

//        db.insert(TABLE_BARANG, BARANG_NAMA, values);
        db.insert(TABLE_BARANG, BARANG_BELI, values);
//        db.insert(TABLE_BARANG, BARANG_STOCK, values);
//        db.insert(TABLE_BARANG, BARANG_HARGA, values);
//
        values.put(BARANG_NAMA, "Cat");
        values.put(BARANG_BELI, "5000");
        values.put(BARANG_STOCK, "30");
        values.put(BARANG_HARGA, "6000");
//
//        db.insert(TABLE_BARANG, BARANG_NAMA, values);
//        db.insert(TABLE_BARANG, BARANG_BELI, values);
        db.insert(TABLE_BARANG, BARANG_STOCK, values);
//        db.insert(TABLE_BARANG, BARANG_HARGA, values);
    }
}
