package com.sukocybercustom.ecommerce.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by suko on 3/7/18.
 */

public class LocalDB extends SQLiteOpenHelper {
    public static String DB_NAME = "suko.db";
    public static int DB_WERSION = 1;
    private Context context;

    public LocalDB(Context context) {
        super(context, DB_NAME, null, DB_WERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String kueri = "CREATE TABLE IF NOT EXISTS cart (" +
                "id integer primary key," +
                "product_id int not null," +
                "value int not null)";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
