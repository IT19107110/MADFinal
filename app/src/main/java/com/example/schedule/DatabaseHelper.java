package com.example.schedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "schedule.db";
    public static final String TABLE_NAME = "schedule_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DATE";
    public static final String COL_3 = "DEPARTURE";
    public static final String COL_4 = "ARRIVAL";
    public static final String COL_5 = "TYPE";
    public static final String COL_6 = "AGENCY";
    public static final String COL_7 = "STATUS";







    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, DEPARTURE TEXT, ARRIVAL TEXT, TYPE TEXT, AGENCY TEXT, STATUS TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String date, String departure, String arrival, String type, String agency, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,date);
        contentValues.put(COL_3,departure);
        contentValues.put(COL_4,arrival);
        contentValues.put(COL_5,type);
        contentValues.put(COL_6,agency);
        contentValues.put(COL_7,status);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }

    public Cursor getId(String departure, String arrival, String type, String agency){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT ID FROM "+TABLE_NAME+ " WHERE DEPARTURE = '"+departure+"' AND ARRIVAL = '"+arrival+"' AND TYPE = '"+type+"' AND AGENCY = '"+agency+"' ", null);
        return res;

    }

    public Cursor getAgency(String date, String departure, String arrival, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT AGENCY FROM "+TABLE_NAME+ " WHERE DATE = '"+date+"' AND DEPARTURE = '"+departure+"' AND ARRIVAL = '"+arrival+"' AND TYPE = '"+type+"' ", null);
        return res;

    }

    public boolean updateData( String id, String date, String departure, String arrival, String type, String agency, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,date);
        contentValues.put(COL_3,departure);
        contentValues.put(COL_4,arrival);
        contentValues.put(COL_5,type);
        contentValues.put(COL_6,agency);
        contentValues.put(COL_7,status);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id} );
        return true;

    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?", new String[] {id});

    }
}
