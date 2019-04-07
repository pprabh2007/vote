package util;

import java.util.regex.Pattern;

public class User {

    private String name;
    private String user_name;
    private String password;
    private int b_day;
    private int b_month;
    private int b_year;
    private String voterID;
    private String sex;
    private String category;
    private String constituency;


    public User() {


    }


    public User(String name, String user_name, String password, String sex, int b_day, int b_month, int b_year, String voterID, String category, String constituency) {

        this.name=name;
        this.user_name = user_name;
        this.password = password;
        this.sex = sex;
        this.b_day = b_day;
        this.b_month = b_month;
        this.b_year = b_year;
        this.voterID = voterID;
        this.category = category;
        this.constituency = constituency;
    }

    public boolean validateEmail()
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (this.user_name == null)
            return false;
        return pat.matcher(this.user_name).matches();
    }

    public boolean validatePassword()
    {
        return this.password.length()>=5;
    }



    public boolean validateConfirmPassword(String password2)
    {
        if(this.password!=null)
            return this.password.equals(password2);
        else return false;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setB_day(int b_day) {
        this.b_day = b_day;
    }

    public void setB_month(int b_month) {
        this.b_month = b_month;
    }

    public void setB_year(int b_year) {
        this.b_year = b_year;
    }

    public void setVoterID(String voterID) {
        this.voterID = voterID;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return this.name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public int getB_day() {
        return b_day;
    }

    public int getB_month() {
        return b_month;
    }

    public int getB_year() {
        return b_year;
    }

    public String getVoterID() {
        return voterID;
    }

    public String getCategory() {
        return category;
    }

    public String getConstituency() {
        return constituency;
    }




}
