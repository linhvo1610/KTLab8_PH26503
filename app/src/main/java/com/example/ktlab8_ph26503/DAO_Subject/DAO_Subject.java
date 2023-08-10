package com.example.ktlab8_ph26503.DAO_Subject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ktlab8_ph26503.DTO.Subject;
import com.example.ktlab8_ph26503.Database.myHelper;

import java.util.ArrayList;

public class DAO_Subject {
    public static final String CREATE_TB_SP = "CREATE TABLE Subject(ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,MaMH TEXT,TenMH TEXT, SoTiet INTEGER);";
    public static final String INSERT_TB_Sub = "insert into Subject values(0,'Mob402','Android Cơ Bản','16')";

    public static final String TB_NAME = "Subject";
    public static final String ID = "ID";
    public static final String MaMH = "MaMH";
    public static final String TenMH = "TenMH";
    public static final String SoTiet = "SoTiet";
    SQLiteDatabase database;
    myHelper MyHelper;

    public DAO_Subject(Context context) {
        MyHelper = new myHelper(context);
    }

    public void opend() {
        database = MyHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }
    public ArrayList<Subject> selectAll() {
        ArrayList<Subject> listsp = new ArrayList<Subject>();
        String[] list_all = new String[]{Subject.COL_NAME_ID, Subject.COL_NAME_MaMH, Subject.COL_NAME_TenMH, Subject.COL_NAME_SoTiet};
        Cursor cursor = database.query(Subject.TB_NAME, list_all, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String mamh = cursor.getString(1);
            String tenmh = cursor.getString(2);
            int sotiet = cursor.getInt(3);

            Subject subject = new Subject();
            subject.setId(id);
            subject.setMamh(mamh);
            subject.setTenmh(tenmh);
            subject.setSotiet(sotiet);

            listsp.add(subject);
            cursor.moveToNext();

        }
        return listsp;

    }
    public long AddSP(Subject subject) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DAO_Subject.MaMH, subject.getMamh());
        contentValues.put(DAO_Subject.TenMH, subject.getTenmh());
        contentValues.put(DAO_Subject.SoTiet, subject.getSotiet());
        try {
            if (database.insert(DAO_Subject.TB_NAME, null, contentValues) == -1) {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
    public int updateSP(Subject subject){
        ContentValues contentValues = new ContentValues();

        contentValues.put(MaMH, subject.getMamh());
        contentValues.put(TenMH,subject.getTenmh());
        contentValues.put(SoTiet,subject.getSotiet());
        try {
            if(database.update(TB_NAME,contentValues,ID+"=?", new String[]{subject.getId()+""})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int deleteSP(Subject subject){
        try {
            if(database.delete(TB_NAME,ID+"=?",  new String[]{subject.getId()+""})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }





}

