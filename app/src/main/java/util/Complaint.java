package util;


public class Complaint {

    private String ID;
    private String title;
    private String domain;
    private int day;
    private int month;
    private int year;
    private String description;
    private int status_stage;
    private String status_description;
    private int upvotes;


    public Complaint(String ID, String title, String domain, int day, int month, int year, String description) {
        this.ID=ID;
        this.title = title;
        this.domain = domain;
        this.day = day;
        this.month = month;
        this.year = year;
        this.description = description;
        this.status_stage=0;
        this.status_description="Launched";
        this.upvotes=(int)(Math.random()*10);
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

    public int getStatus_stage() {
        return status_stage;
    }

    public void setStatus_stage(int status_stage) {
        this.status_stage = status_stage;
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

    public String getDate()
    {
        String day_string=this.day<10?"0"+this.day:""+this.day;
        String month_string=this.month<10?"0"+this.month:""+this.month;
        String year_string=""+this.year;

        return (day_string+"/"+month_string+"/"+year_string);
    }


}
