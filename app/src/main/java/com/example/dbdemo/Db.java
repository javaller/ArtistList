package com.example.dbdemo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.dbdemo.net.model.Artist;

import java.util.List;

public class Db extends SQLiteOpenHelper {
    private static Db instance;

    public static final String DB_NAME = "dbdbdbd";
    public static final int VERSTION = 1;

    private Context context;

    public static Db getInstance(Context context) {
        if (instance == null) {
            instance = new Db(context);
        }

        return instance;
    }

    private Db (Context context) {
        super(context, DB_NAME, null, VERSTION);

        this.context = context;
    }

    public static final String TABLE1_NAME = "table1";
    public static final String CREATE_TABLE1 =
            "create table " + TABLE1_NAME + " ( " +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT " +
                    ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE1_NAME);
        db.execSQL(CREATE_TABLE1);
    }

    public void addName (String name) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name", name);

        try {
            db.beginTransaction();

            db.insert(TABLE1_NAME, null, cv);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public Cursor getNames() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query( // <-- это SELECT!
                TABLE1_NAME,
                null, // columns list
                null, null, // WHERE
                null, null, // "всегда" null
                null // ORDER BY
        );

//        String letter = "v";
//
//        Cursor cursor = db.query( // <-- это SELECT!
//                TABLE1_NAME,
//                new String[] { "name" }, // columns list
//                "name LIKE ?", new String[] { letter + "%" }, // WHERE
//                null, null, // "всегда" null
//                "name" // ORDER BY
//        );

//
        Log.d("happy", "count " + cursor.getCount());
//
////        int nameIdx = cursor.getColumnIndex("name");
//        int nameIdx = cursor.getColumnIndexOrThrow("name");
//
//        if (cursor.moveToFirst()) {
//            do {
//                // TODO
//                String name = cursor.getString(nameIdx);
//                Log.d("happy", "name " + name);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();

        return cursor;
    }

    public void saveArtistList(List<Artist> body) {
        SQLiteDatabase db = getWritableDatabase();

        try {
            db.beginTransaction();

            for (Artist artist : body) {
                ContentValues cv = new ContentValues();
                cv.put("name", artist.name);

                db.insert(TABLE1_NAME, null, cv);
            }

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }
}
