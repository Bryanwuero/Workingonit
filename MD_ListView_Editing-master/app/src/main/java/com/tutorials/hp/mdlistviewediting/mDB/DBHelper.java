package com.tutorials.hp.mdlistviewediting.mDB;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tutorials.hp.mdlistviewediting.mData.Activity;

import java.util.ArrayList;

/**
 * Created by arroy_000 on 9/7/2016.
 */
public class DBHelper extends SQLiteOpenHelper{

    private static final String TABLE = "Activities";
    private static final String DATABASE = "demo.db";
    private static final int VERSION = 1;

    private static final String C_ID = "id";
    private static final String C_NAME = "name";
    private static final String C_DESCRIPTION = "description";
    private static final String C_START = "startDate";
    private static final String C_FINISH= "finishDate";


    public DBHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String creationQuery = "CREATE TABLE " +
                TABLE +
                " ("+
                C_ID +
                "INTEGER PRIMARY KEY, "+
                C_NAME +
                " TEXT, " +
                C_DESCRIPTION +
                " TEXT, " +
                C_START +
                " TEXT, " +
                C_FINISH +
                " TEXT)";

        sqLiteDatabase.execSQL(creationQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String[] tables = {TABLE};

        sqLiteDatabase.execSQL("DROP TABLE IF EXIST ?", tables);
        onCreate(sqLiteDatabase);
    }

    public void saveRecord(String name, String description, String start, String finish){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(C_NAME, name);
        cv.put(C_DESCRIPTION, description);
        cv.put(C_START, start);
        cv.put(C_FINISH, finish);
        db.insert(TABLE, null, cv);
    }

    public int getRecords(String name){

        SQLiteDatabase db = getWritableDatabase();
        int result = -1;

        String selection = "name = ?";
        String[] params = {name};

        Cursor c = db.query(TABLE, null, selection, params, null, null, null);

        if(c.moveToFirst()){
            result = c.getInt(0);

        }

        return result;
    }

    public int deleteRecords(String name) {

        SQLiteDatabase db = getWritableDatabase();
        String selection = "name = ?";
        String[] params = {name};
        return db.delete(TABLE, selection, params);
    }

    public ArrayList<Activity> getActivities(){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Activity> resultado = new ArrayList<Activity>();

        Cursor c = db.query(TABLE, null, null, null, null, null, null);

        if(c.moveToFirst()){
            do {
                resultado.add(new Activity(c.getString(1), c.getString(2), c.getString(3), c.getString(4)));
            }
            while (c.moveToNext());
        }
        return resultado;
    }
}