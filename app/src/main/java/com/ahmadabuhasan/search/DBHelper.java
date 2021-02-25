package com.ahmadabuhasan.search;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ahmad Abu Hasan on 30/11/2020, 24/02/2021
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
        db.execSQL("INSERT INTO " + TABLE_BARANG + "(BARANG_ID, BARANG_NAMA, BARANG_BELI, BARANG_STOCK, BARANG_HARGA) 
                   VALUES 
                   (1, 'Kuas', '1000', '10', '2000')");
        db.execSQL("INSERT INTO " + TABLE_BARANG + "(BARANG_ID, BARANG_NAMA, BARANG_BELI, BARANG_STOCK, BARANG_HARGA) 
                   VALUES 
                   (2, 'Paku', '3000', '20', '4000')");
        db.execSQL("INSERT INTO " + TABLE_BARANG + "(BARANG_ID, BARANG_NAMA, BARANG_BELI, BARANG_STOCK, BARANG_HARGA) 
                   VALUES 
                   (3, 'Cat', '5000', '30', '6000')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BARANG);

    }
}
