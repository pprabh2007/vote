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
import util.Complaint;
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

        command="CREATE TABLE "+CONSTANTS.COMPLAINT_TABLE+"("+CONSTANTS.ID+" TEXT PRIMARY KEY,"+CONSTANTS.TITLE+" TEXT,"+CONSTANTS.DESCRIPTION+" TEXT,"+CONSTANTS.DOMAIN+" TEXT,"+CONSTANTS.LAUNCHER+" TEXT,"+CONSTANTS.LAUNCH_D+" INTEGER,"+CONSTANTS.LAUNCH_M+" INTEGER,"+CONSTANTS.LAUNCH_Y+" INTEGER,"+CONSTANTS.STATUS_NO+" INTEGER,"+CONSTANTS.STATUS_DESC+" TEXT,"+CONSTANTS.CONTRACTOR+" TEXT,"+CONSTANTS.CONSTITUENCY+" TEXT,"+CONSTANTS.UPVOTES+" INTEGER"+")";
        db.execSQL(command);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String command="DROP TABLE IF EXISTS "+CONSTANTS.REGISTRATION_TABLE;
        db.execSQL(command);
        command="DROP TABLE IF EXISTS "+CONSTANTS.COMPLAINT_TABLE;
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

    public List<User> getVoters(String constituency, String category_code)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+CONSTANTS.REGISTRATION_TABLE+" WHERE "+CONSTANTS.CONSTITUENCY+"=?"+" AND "+CONSTANTS.CATEGORY+"=?", new String[]{constituency, category_code});

        List<User> user_list=new ArrayList<User>();

        if(cur.moveToFirst())
        {
            do {

                User iter=new User();
                iter.setName(cur.getString(0));
                iter.setUser_name(cur.getString(1));
                iter.setVoterID(cur.getString(9));

            }while (cur.moveToNext());
        }

        return user_list;

    }

    public User getDetails(String username)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+CONSTANTS.REGISTRATION_TABLE+" WHERE "+CONSTANTS.USER_NAME+"=?", new String[]{username});

        User this_user=new User();
        if(cur.moveToFirst() && cur.getCount()>0)
        {
            this_user.setPassword("");
            this_user.setName(cur.getString(0));
            this_user.setUser_name(cur.getString(1));
            this_user.setB_day(cur.getInt(2));
            this_user.setB_month(cur.getInt(3));
            this_user.setB_year(cur.getInt(4));
            this_user.setCategory(cur.getString(5));
            this_user.setConstituency(cur.getString(6));
            this_user.setSex(cur.getString(7));
            this_user.setVoterID(cur.getString(9));
        }
        //Log.e("LOG", category);
        return this_user;
    }

    public void addComplaint(Complaint complaint)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues con=new ContentValues();
        con.put(CONSTANTS.ID, complaint.getID());
        con.put(CONSTANTS.TITLE, complaint.getTitle());
        con.put(CONSTANTS.DESCRIPTION, complaint.getDescription());
        con.put(CONSTANTS.DOMAIN, complaint.getDomain());
        con.put(CONSTANTS.LAUNCHER, complaint.getUser_name_launcher());
        con.put(CONSTANTS.LAUNCH_D, complaint.getDay());
        con.put(CONSTANTS.LAUNCH_M, complaint.getMonth());
        con.put(CONSTANTS.LAUNCH_Y, complaint.getYear());
        con.put(CONSTANTS.STATUS_NO, complaint.getStatus_stage());
        con.put(CONSTANTS.STATUS_DESC, complaint.getStatus_description());
        con.put(CONSTANTS.CONTRACTOR, complaint.getContractor());
        con.put(CONSTANTS.CONSTITUENCY, complaint.getConstituency());
        con.put(CONSTANTS.UPVOTES, complaint.getUpvotes());

        db.insert(CONSTANTS.COMPLAINT_TABLE, null, con);

    }

    public List<Complaint> getAllComplaints()
    {
        SQLiteDatabase db=this.getReadableDatabase();

        String command="SELECT * FROM "+CONSTANTS.COMPLAINT_TABLE;
        Cursor cur=db.rawQuery(command, null);

        List<Complaint> com_list=new ArrayList<Complaint>();

        if(cur.moveToFirst())
        {
            do
            {
                Complaint iter=new Complaint();
                iter.setID(cur.getString(0));
                iter.setTitle(cur.getString(1));
                iter.setDescription(cur.getString(2));
                iter.setDomain(cur.getString(3));
                iter.setUser_name_launcher(cur.getString(4));
                iter.setDay(cur.getInt(5));
                iter.setMonth(cur.getInt(6));
                iter.setYear(cur.getInt(7));
                iter.setStatus_stage(cur.getInt(8));
                iter.setStatus_description(cur.getString(9));
                iter.setContractor(cur.getString(10));
                iter.setConstituency(cur.getString(11));
                iter.setUpvotes(cur.getInt(12));

                com_list.add(iter);


            }while(cur.moveToNext());
        }

        return com_list;

    }

}
