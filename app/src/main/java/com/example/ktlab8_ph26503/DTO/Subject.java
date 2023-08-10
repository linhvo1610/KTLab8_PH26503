package com.example.ktlab8_ph26503.DTO;

public class Subject {
    private int id;
    private String mamh;
    private String tenmh;
    private int sotiet;
    public static final String TB_NAME = "Subject";
    public static final String COL_NAME_ID = "ID";
    public static final String COL_NAME_MaMH = "MaMH";
    public static final String COL_NAME_TenMH = "TenMH";
    public static final String COL_NAME_SoTiet = "SoTiet";

    public Subject(int id, String mamh, String tenmh, int sotiet) {
        this.id = id;
        this.mamh = mamh;
        this.tenmh = tenmh;
        this.sotiet = sotiet;
    }
    public Subject(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMamh() {
        return mamh;
    }

    public void setMamh(String mamh) {
        this.mamh = mamh;
    }

    public String getTenmh() {
        return tenmh;
    }

    public void setTenmh(String tenmh) {
        this.tenmh = tenmh;
    }

    public int getSotiet() {
        return sotiet;
    }

    public void setSotiet(int sotiet) {
        this.sotiet = sotiet;
    }
}
