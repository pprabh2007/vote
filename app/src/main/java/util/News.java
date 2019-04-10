package util;


import java.util.Calendar;

public class News {

    private String title;
    private String description;
    private int day;
    private int month;
    private int year;
    private String constituency;
    private String publisher;

    public News() {
        Calendar now = Calendar.getInstance();   // Gets the current date and time
        this.year = now.get(Calendar.YEAR);
        this.month=now.get(Calendar.MONTH) +1;
        this.day=now.get(Calendar.DAY_OF_MONTH);
    }



    public News(String title, String description, String constituency, String publisher) {

        this.title = title;
        this.description = description;

        Calendar now = Calendar.getInstance();   // Gets the current date and time
        this.year = now.get(Calendar.YEAR);
        this.month=now.get(Calendar.MONTH) +1;
        this.day=now.get(Calendar.DAY_OF_MONTH);

        this.constituency = constituency;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDate()
    {
        String day_string=this.day<10?"0"+this.day:""+this.day;
        String month_string=this.month<10?"0"+this.month:""+this.month;
        String year_string=""+this.year;

        return (day_string+"/"+month_string+"/"+year_string);
    }



}
