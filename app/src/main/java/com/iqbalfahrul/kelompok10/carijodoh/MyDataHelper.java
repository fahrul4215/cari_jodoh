package com.iqbalfahrul.kelompok10.carijodoh;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataHelper extends SQLiteOpenHelper {
    private static final String NAMA_DB = "carijodoh.db";
    private static final String NAMA_TABEL = "user";
    private static final int VERSI_DB = 2;

    private static final String ID = "_id";
    private static final String NAMA = "nama";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String JKL = "jkl";
    private static final String ALAMAT = "alamat";
    private static final String FOTO = "foto";
    private static final String LEVEL = "level";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + NAMA_TABEL + "("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAMA + " VARCHAR(50), "
                    + USERNAME + " VARCHAR(50),"
                    + PASSWORD + " VARCHAR(50), "
                    + JKL + " VARCHAR(5), "
                    + ALAMAT + " VARCHAR(200), "
                    + FOTO + " INTEGER, "
                    + LEVEL + " INTEGER );";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NAMA_TABEL;

    MyDataHelper(Context cont) {
        super(cont, NAMA_DB, null, VERSI_DB);
    }

    private void insertAdmin(SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(NAMA, "ADMIN");
        cv.put(USERNAME, "ADMIN");
        cv.put(PASSWORD, "ADMIN");
        cv.put(JKL, "null");
        cv.put(ALAMAT, "null");
        cv.put(FOTO, "null");
        cv.put(LEVEL, 1);
        db.insert(NAMA_TABEL,null,cv);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    //melakukan pengupdate an table yang baru
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < VERSI_DB) {
            db.execSQL(DROP_TABLE);
            db.execSQL(CREATE_TABLE);

            //insert admin
            insertAdmin(db);
        }
    }
}