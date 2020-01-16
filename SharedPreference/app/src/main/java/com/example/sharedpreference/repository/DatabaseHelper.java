package com.example.sharedpreference.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // DatabaseHelper Function :
    // as the helper to create a DB and table that we need

    public DatabaseHelper(Context context){
        // 4 parameters : context, nama_database, factory, version
        super(context, "NoteDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Set a query to create a table using string
        String query = "CREATE TABLE Note(" +
                "IDNote INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Title TEXT NOT NULL," +
                "Content TEXT NOT NULL)";

        // Exec query
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // onUpgrade means the older table should be dropped first,
        // then create a new table by calling onCreate() method
        String query = "DROP TABLE IF EXISTS Note";
        db.execSQL(query);
        onCreate(db);
    }
}
