package util;


public class News {

    private String title;
    private String description;
    private String date;


    public News() {


    }


    public News(String title,String description,String Date) {

        this.title=title;
        this.description=description;
        this.date=Date;
    }
    public void settitle(String title)
    {
        this.title=title;
    }
    public String gettitle() {
        return title;
    }
    public String getDescription()
    {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }
    public void setDate(String date)
    {
        this.date=date;
    }
    public String getDate()
    {
        return this.date;
    }
}
