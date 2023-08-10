package com.example.ktlab8_ph26503.Database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ktlab8_ph26503.DAO_Subject.DAO_Subject;

public class myHelper extends SQLiteOpenHelper {
    static final String db_NAME = "QUANLY.db";
    static final int VERSION = 3;

    public myHelper(Context context) {
        super(context, db_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DAO_Subject.CREATE_TB_SP);
        db.execSQL(DAO_Subject.INSERT_TB_Sub);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DAO_Subject.TB_NAME);




    }
}



