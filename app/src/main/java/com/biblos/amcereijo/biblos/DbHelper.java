package com.biblos.amcereijo.biblos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "biblos";
    private static final String ORDER_TABLE_NAME = "orders";
    private static final String ORDER_ID = "id";
    private static final String ORDER_JSON = "json";
    private static final String ORDER_TABLE_CREATE =
            "CREATE TABLE " + ORDER_TABLE_NAME + " (" +
                    ORDER_ID +" TEXT, " +
                    ORDER_JSON + " TEXT)";

    DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ORDER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //nothing
    }

    public void save(String id, String json) {
        ContentValues values = new ContentValues();
        values.put(ORDER_ID, id);
        values.put(ORDER_JSON, json);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ORDER_TABLE_NAME, null, values);
        db.close();
    }

    public List<String> findAll(){
        String sort = ORDER_ID + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                ORDER_TABLE_NAME,
                new String[]{ORDER_JSON},
                null,
                null,
                null,
                null,
                sort
        );
        c.moveToFirst();
        List<String> jsons = new ArrayList<>(c.getCount());
        do{
            jsons.add(c.getString(0));
        } while (c.moveToNext());
        c.close();
        db.close();
        return jsons;
    }
}
