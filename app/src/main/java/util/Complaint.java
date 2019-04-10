package util;


import android.util.Log;

import com.example.myapplication.Databasehelper;

import java.util.Calendar;

public class Complaint {

    private String ID;
    private String title;
    private String description;
    private String domain;
    private int day;
    private int month;
    private int year;
    private String user_name_launcher;
    private int upvotes;
    private int bid_amt;
    private String status_description;
    private String contractor;
    private String constituency;
    private int best_bid;



    public Complaint()
    {

        this.bid_amt=-1;
        this.status_description="Launched";
        this.contractor="None";
        this.upvotes=0;
        Calendar now = Calendar.getInstance();   // Gets the current date and time
        this.year = now.get(Calendar.YEAR);
        this.month=now.get(Calendar.MONTH) +1;
        this.day=now.get(Calendar.DAY_OF_MONTH);
        this.ID="CM"+(long)(Math.random()*1000000);


    }

    public Complaint(String title, String description, String domain, String user_name_launcher)
    {
        this.title=title;
        this.domain=domain;
        this.description=description;
        this.bid_amt=-1;
        this.status_description="Launched";
        this.contractor="None";
        this.upvotes=0;
        this.user_name_launcher=user_name_launcher;

        Calendar now = Calendar.getInstance();   // Gets the current date and time
        this.year = now.get(Calendar.YEAR);
        this.month=now.get(Calendar.MONTH) +1;
        this.day=now.get(Calendar.DAY_OF_MONTH);


        this.ID="CM"+(long)(Math.random()*1000000);




    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }



    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBid_amt() {
        return bid_amt;
    }

    public void setBid_amt(int bid_amt) {
        this.bid_amt = bid_amt;
    }

    public String getStatus_description() {
        return status_description;
    }

    public void setStatus_description(String status_description) {
        this.status_description = status_description;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public int getBest_bid() {
        return best_bid;
    }

    public void setBest_bid(int best_bid) {
        this.best_bid = best_bid;
    }






    public String getDate()
    {
        String day_string=this.day<10?"0"+this.day:""+this.day;
        String month_string=this.month<10?"0"+this.month:""+this.month;
        String year_string=""+this.year;

        return (day_string+"/"+month_string+"/"+year_string);
    }

    public String getUser_name_launcher() {
        return user_name_launcher;
    }

    public void setUser_name_launcher(String user_name_launcher) {
        this.user_name_launcher = user_name_launcher;
    }

}
