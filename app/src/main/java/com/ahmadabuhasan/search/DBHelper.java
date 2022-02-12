package com.ahmadabuhasan.search;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ahmad Abu Hasan on 30/11/2020, 25/02/2021, 12/02/2022
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "search.db";

    public static final String TABLE_DATA = "data";

    public static final String DATA_ID = "DataId";
    public static final String DATA_NAME = "NameProduct";
    public static final String DATA_BUY = "Buy";
    public static final String DATA_STOCK = "Stock";
    public static final String DATA_PRICE = "Price";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_DATA +
                "(DataId INTEGER PRIMARY KEY, NameProduct TEXT, Buy TEXT, Stock TEXT, Price TEXT)");
        db.execSQL("INSERT INTO " + TABLE_DATA +
                "(DataId, NameProduct, Buy, Stock, Price) VALUES (1, 'Facebook', '111', '11','1111')");
        db.execSQL("INSERT INTO " + TABLE_DATA +
                "(DataId, NameProduct, Buy, Stock, Price) VALUES (2, 'Instagram', '222', '22','2222')");
        db.execSQL("INSERT INTO " + TABLE_DATA +
                "(DataId, NameProduct, Buy, Stock, Price) VALUES (3, 'WhatsApp', '333', '33','3333')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
        onCreate(db);
    }
}