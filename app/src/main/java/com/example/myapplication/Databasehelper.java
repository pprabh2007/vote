package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import util.CONSTANTS;
import util.Complaint;
import util.News;
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

        command="CREATE TABLE "+CONSTANTS.COMPLAINT_TABLE+"("+CONSTANTS.ID+" TEXT PRIMARY KEY,"+CONSTANTS.TITLE+" TEXT,"+CONSTANTS.DESCRIPTION+" TEXT,"+CONSTANTS.DOMAIN+" TEXT,"+CONSTANTS.LAUNCHER+" TEXT,"+CONSTANTS.LAUNCH_D+" INTEGER,"+CONSTANTS.LAUNCH_M+" INTEGER,"+CONSTANTS.LAUNCH_Y+" INTEGER,"+CONSTANTS.BID_AMT+" INTEGER,"+CONSTANTS.STATUS_DESC+" TEXT,"+CONSTANTS.CONTRACTOR+" TEXT,"+CONSTANTS.CONSTITUENCY+" TEXT,"+CONSTANTS.UPVOTES+" INTEGER,"+CONSTANTS.UPVOTES_STRING+" TEXT"+")";
        db.execSQL(command);

        command="CREATE TABLE "+CONSTANTS.NEWS_TABLE+"("+CONSTANTS.NEWS_TITLE+" TEXT,"+CONSTANTS.NEWS_DESCRIPTION+" TEXT,"+CONSTANTS.NEWS_PUBLISHER+" TEXT,"+CONSTANTS.CONSTITUENCY+" TEXT,"+CONSTANTS.NEWS_DAY+" INTEGER,"+CONSTANTS.NEWS_MONTH+" INTEGER,"+CONSTANTS.NEWS_YEAR+" INTEGER"+")";
        db.execSQL(command);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String command="DROP TABLE IF EXISTS "+CONSTANTS.REGISTRATION_TABLE;
        db.execSQL(command);
        command="DROP TABLE IF EXISTS "+CONSTANTS.COMPLAINT_TABLE;
        db.execSQL(command);
        command="DROP TABLE IF EXISTS "+CONSTANTS.NEWS_TABLE;
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

    public List<User> getUsers(String constituency, String category_code)
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

                user_list.add(iter);
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
        con.put(CONSTANTS.BID_AMT, complaint.getBid_amt());
        con.put(CONSTANTS.STATUS_DESC, complaint.getStatus_description());
        con.put(CONSTANTS.CONTRACTOR, complaint.getContractor());
        con.put(CONSTANTS.CONSTITUENCY, complaint.getConstituency());
        con.put(CONSTANTS.UPVOTES, complaint.getUpvotes());
        con.put(CONSTANTS.UPVOTES_STRING, complaint.getUpvotes_string());

        db.insert(CONSTANTS.COMPLAINT_TABLE, null, con);

    }

    public List<Complaint> getAllComplaints(String constituency)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        String command="SELECT * FROM "+CONSTANTS.COMPLAINT_TABLE+" WHERE "+CONSTANTS.CONSTITUENCY+"=?";
        Cursor cur=db.rawQuery(command, new String[]{constituency});

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
                iter.setBid_amt(cur.getInt(8));
                iter.setStatus_description(cur.getString(9));
                iter.setContractor(cur.getString(10));
                iter.setConstituency(cur.getString(11));
                iter.setUpvotes(cur.getInt(12));
                iter.setUpvotes_string(cur.getString(13));

                com_list.add(iter);


            }while(cur.moveToNext());
        }

        return com_list;

    }

    public Complaint getComplaint(String ID)
    {
        Complaint temp=new Complaint();

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+CONSTANTS.COMPLAINT_TABLE+" WHERE "+CONSTANTS.ID+"=?", new String[]{ID});

        if(cur.getCount()>0)
        {
            if(cur.moveToFirst())
            {
                temp.setID(cur.getString(0));
                temp.setTitle(cur.getString(1));
                temp.setDescription(cur.getString(2));
                temp.setDomain(cur.getString(3));
                temp.setUser_name_launcher(cur.getString(4));
                temp.setDay(cur.getInt(5));
                temp.setMonth(cur.getInt(6));
                temp.setYear(cur.getInt(7));
                temp.setBid_amt(cur.getInt(8));
                temp.setStatus_description(cur.getString(9));
                temp.setContractor(cur.getString(10));
                temp.setConstituency(cur.getString(11));
                temp.setUpvotes(cur.getInt(12));
                temp.setUpvotes_string(cur.getString(13));
                return temp;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    public HashMap<String, String> getStatus(String ID)
    {
        HashMap<String, String> status_map=new HashMap<String, String>();

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+CONSTANTS.COMPLAINT_TABLE+" WHERE "+CONSTANTS.ID+"=?", new String[]{ID});
        if(cur.moveToFirst())
        {
            String Date=CONSTANTS.getDate(cur.getInt(5), cur.getInt(6), cur.getInt(7));
            String Status=cur.getString(9);
            String Contra=cur.getString(10);

            status_map.put("DATE", Date);
            status_map.put("STATUS", Status);
            status_map.put("CONTRACTOR", Contra);

        }


        return status_map;

    }

    public void addNews(News newNews)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        ContentValues con=new ContentValues();
        con.put(CONSTANTS.NEWS_TITLE, newNews.getTitle());
        con.put(CONSTANTS.NEWS_DESCRIPTION, newNews.getDescription());
        con.put(CONSTANTS.NEWS_PUBLISHER, newNews.getPublisher());
        con.put(CONSTANTS.CONSTITUENCY, newNews.getConstituency());
        con.put(CONSTANTS.NEWS_DAY, newNews.getDay());
        con.put(CONSTANTS.NEWS_MONTH, newNews.getMonth());
        con.put(CONSTANTS.NEWS_YEAR, newNews.getYear());

        db.insert(CONSTANTS.NEWS_TABLE, null, con);
    }

    public List<News> getNews(String constituency)
    {
        List<News> news_list=new ArrayList<>();

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+ CONSTANTS.NEWS_TABLE+" WHERE "+CONSTANTS.CONSTITUENCY+"=?", new String[]{constituency});
        if(cur.moveToFirst())
        {
            do {
                News iter = new News();
                iter.setTitle(cur.getString(0));
                iter.setDescription(cur.getString(1));
                iter.setPublisher(cur.getString(2));
                iter.setConstituency(cur.getString(3));
                iter.setDay(cur.getInt(4));
                iter.setMonth(cur.getInt(5));
                iter.setYear(cur.getInt(6));

                news_list.add(iter);

            }while(cur.moveToNext());
        }

        return news_list;

    }


    public List<Complaint> filterComplaints(User THIS_USER_OBJECT, int REQUEST_CODE)
    {
        List<Complaint> filtered_complaints=new ArrayList<Complaint>();

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur;
        switch(REQUEST_CODE)
        {
            case 1:
                cur=db.rawQuery("SELECT * FROM "+CONSTANTS.COMPLAINT_TABLE+" WHERE "+CONSTANTS.STATUS_DESC+"=?", new String[]{"Launched"});
                break;

            case 2:
                cur=db.rawQuery("SELECT * FROM "+CONSTANTS.COMPLAINT_TABLE+" WHERE "+CONSTANTS.STATUS_DESC+"=?"+" AND "+CONSTANTS.CONTRACTOR+"=?", new String[]{"Assigned", THIS_USER_OBJECT.getUser_name()});
                break;
            case 3:
                cur=db.rawQuery("SELECT * FROM "+CONSTANTS.COMPLAINT_TABLE+" WHERE "+CONSTANTS.STATUS_DESC+"=?"+" AND "+CONSTANTS.CONTRACTOR+"=?", new String[]{"Completed", THIS_USER_OBJECT.getUser_name()});

                break;
            case 4:
                cur=db.rawQuery("SELECT * FROM "+CONSTANTS.COMPLAINT_TABLE+" WHERE "+CONSTANTS.STATUS_DESC+"=?"+" AND "+CONSTANTS.BID_AMT+"!=?"+" AND "+CONSTANTS.CONSTITUENCY+"=?", new String[]{"Launched", "-1", THIS_USER_OBJECT.getConstituency()});
                break;
            case 5:
                cur=db.rawQuery("SELECT * FROM "+CONSTANTS.COMPLAINT_TABLE+" WHERE "+CONSTANTS.CONSTITUENCY+"=?"+" AND "+CONSTANTS.BID_AMT+"=?", new String[]{THIS_USER_OBJECT.getConstituency(), "-1"});
                break;
            case 6:
                cur=db.rawQuery("SELECT * FROM "+CONSTANTS.COMPLAINT_TABLE+" WHERE "+CONSTANTS.CONSTITUENCY+"=?"+" AND "+CONSTANTS.STATUS_DESC+"=?", new String[]{THIS_USER_OBJECT.getConstituency(), "Assigned"});
                break;
            case 7:
                cur=db.rawQuery("SELECT * FROM "+CONSTANTS.COMPLAINT_TABLE+" WHERE "+CONSTANTS.CONSTITUENCY+"=?"+" AND "+CONSTANTS.STATUS_DESC+"=?", new String[]{THIS_USER_OBJECT.getConstituency(), "Completed"});
                break;

            default:
                cur=db.rawQuery("SELECT * FROM "+CONSTANTS.COMPLAINT_TABLE, null);
                break;
        }
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
                iter.setBid_amt(cur.getInt(8));
                iter.setStatus_description(cur.getString(9));
                iter.setContractor(cur.getString(10));
                iter.setConstituency(cur.getString(11));
                iter.setUpvotes(cur.getInt(12));
                iter.setUpvotes_string(cur.getString(13));

                filtered_complaints.add(iter);


            }while(cur.moveToNext());
        }

        Collections.reverse(filtered_complaints);

        return filtered_complaints;

    }

    public void updateBid(Complaint THIS_COMPLAINT, User THIS_USER_OBJECT, int newBid)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        if(newBid<THIS_COMPLAINT.getBid_amt() || THIS_COMPLAINT.getBid_amt()==-1)
        {
            THIS_COMPLAINT.setBid_amt(newBid);
            THIS_COMPLAINT.setContractor(THIS_USER_OBJECT.getUser_name());

            ContentValues con=new ContentValues();
            con.put(CONSTANTS.BID_AMT, THIS_COMPLAINT.getBid_amt());
            con.put(CONSTANTS.CONTRACTOR, THIS_COMPLAINT.getContractor());

            db.update(CONSTANTS.COMPLAINT_TABLE, con, CONSTANTS.ID+"=?", new String[]{THIS_COMPLAINT.getID()});

        }
        else
        {
            //do nothing
        }

    }

    public void upvoteIssue(Complaint THIS_COMPLAINT, User THIS_USER_OBJECT)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        THIS_COMPLAINT.setUpvotes(THIS_COMPLAINT.getUpvotes()+1);
        THIS_COMPLAINT.appendUpvote(THIS_USER_OBJECT.getUser_name());

        ContentValues con=new ContentValues();
        con.put(CONSTANTS.UPVOTES, THIS_COMPLAINT.getUpvotes());
        con.put(CONSTANTS.UPVOTES_STRING, THIS_COMPLAINT.getUpvotes_string());
        db.update(CONSTANTS.COMPLAINT_TABLE, con, CONSTANTS.ID+"=?", new String[]{THIS_COMPLAINT.getID()});
    }

    public void setAssigned(Complaint THIS_COMPLAINT)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        THIS_COMPLAINT.setStatus_description("Assigned");

        ContentValues con=new ContentValues();
        con.put(CONSTANTS.STATUS_DESC, THIS_COMPLAINT.getStatus_description());
        db.update(CONSTANTS.COMPLAINT_TABLE, con, CONSTANTS.ID+"=?", new String[]{THIS_COMPLAINT.getID()});

    }
<<<<<<< HEAD
}
=======
}
>>>>>>> d06752ec45b10cb9daa53767fb9a1713b1565862
