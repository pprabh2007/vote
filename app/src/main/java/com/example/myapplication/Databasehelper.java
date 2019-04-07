package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import util.CONSTANTS;
import util.User;

public class Databasehelper extends SQLiteOpenHelper {
    public Databasehelper(Context context) {
            super(context, CONSTANTS.DATABASE_NAME, null, CONSTANTS.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String command="CREATE TABLE "+ CONSTANTS.REGISTRATION_TABLE+"("+CONSTANTS.NAME+" TEXT,"+CONSTANTS.USER_NAME+" TEXT PRIMARY KEY,"+CONSTANTS.B_DAY+" INTEGER,"+CONSTANTS.B_MONTH+" INTEGER,"+CONSTANTS.B_YEAR+" INTEGER,"+CONSTANTS.CATEGORY+ " TEXT,"+CONSTANTS.CONSTITUENCY+" TEXT,"+CONSTANTS.SEX+" TEXT,"+CONSTANTS.PASSWORD+" TEXT,"+CONSTANTS.VOTER_ID+" TEXT"+")";
        //String command="CREATE TABLE "+CONSTANTS.REGISTRATION_TABLE+"("+CONSTANTS.USER_NAME+" TEXT)";
        db.execSQL(command);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String command="DROP TABLE IF EXISTS "+CONSTANTS.REGISTRATION_TABLE;
        db.execSQL(command);
        onCreate(db);
    }

    //CURD

    public void addUser(User newUser)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues con=new ContentValues();
        con.put(CONSTANTS.NAME, newUser.getName());
        con.put(CONSTANTS.USER_NAME, newUser.getUser_name());
        con.put(CONSTANTS.PASSWORD, newUser.getPassword());
        con.put(CONSTANTS.VOTER_ID, newUser.getVoterID());
        con.put(CONSTANTS.CATEGORY, newUser.getCategory());
        con.put(CONSTANTS.CONSTITUENCY, newUser.getConstituency());
        con.put(CONSTANTS.SEX, newUser.getSex());
        con.put(CONSTANTS.B_DAY, newUser.getB_day());
        con.put(CONSTANTS.B_MONTH, newUser.getB_month());
        con.put(CONSTANTS.B_YEAR, newUser.getB_year());

        db.insert(CONSTANTS.REGISTRATION_TABLE, null, con);
    }

    public boolean checkIfPresent(String emailid)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+CONSTANTS.REGISTRATION_TABLE+" WHERE "+CONSTANTS.USER_NAME+"=?", new String[]{emailid});

        return cur.getCount()>0;
    }

    public String getCategoryOf(String email, String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+CONSTANTS.REGISTRATION_TABLE+" WHERE "+CONSTANTS.USER_NAME+"=?"+" AND "+CONSTANTS.PASSWORD+"=?", new String[]{email, password});

        String category="NA";
        if(cur.moveToFirst() && cur.getCount()>0)
        {
            category=cur.getString(5);
        }
        //Log.e("LOG", category);
        return category;
    }

    public List<String> getAllConstituencies()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+CONSTANTS.REGISTRATION_TABLE+" WHERE "+CONSTANTS.CATEGORY+"=?", new String[]{"R"});

        List<String> cons_list=new ArrayList<String>();

        if(cur.moveToFirst())
        {
            do {
                cons_list.add(cur.getString(6));
            }while (cur.moveToNext());
        }

        return cons_list;

    }
}
