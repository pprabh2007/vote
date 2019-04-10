package util;

import java.util.List;

public class CONSTANTS
{
    public static final String DATABASE_NAME="DATABASE.db";
    public static final int VERSION=8;

    /* TABLE 1*/
    public static final String REGISTRATION_TABLE="REGISTRATION_TABLE";
    public static final String USER_NAME="USER_NAME";
    public static final String NAME="NAME";
    public static final String PASSWORD="PASSWORD";
    public static final String VOTER_ID="VOTER_ID";
    public static final String SEX="SEX";
    public static final String B_DAY="B_DAY";
    public static final String B_MONTH="B_MONTH";
    public static final String B_YEAR="B_YEAR";
    public static final String CATEGORY="CATEGORY";
    public static final String CONSTITUENCY="CONSTITUENCY";

    /* TABLE 2 */
    public static final String COMPLAINT_TABLE="COMPLAINT_TABLE";

    public static final String ID="ID";
    public static final String TITLE="TITLE";
    public static final String DESCRIPTION="DESCRIPTION";
    public static final String DOMAIN="DOMAIN";
    public static final String LAUNCHER="LAUNCHER";
    public static final String LAUNCH_D="LAUNCH_D";
    public static final String LAUNCH_M="LAUNCH_M";
    public static final String LAUNCH_Y="LAUNCH_Y";
    public static final String BID_AMT="BID_AMT";
    public static final String STATUS_DESC="STATUS_DESC";
    public static final String CONTRACTOR="CONTRACTOR";
    public static final String UPVOTES="UPVOTES";
    public static final String UPVOTES_STRING="UPVOTES_STRING";
    //public static final String CONSTITUENCY="CONSTITUENCY";

    /* TABLE 3 */
    public static final String NEWS_TABLE="NEWS_TABLE";

    public static final String NEWS_TITLE="NEWS_TITLE";
    public static final String NEWS_DESCRIPTION="NEWS_DESCRIPTION";
    public static final String NEWS_DAY="NEWS_DAY";
    public static final String NEWS_MONTH="NEWS_MONTH";
    public static final String NEWS_YEAR="NEWS_YEAR";
    public static final String NEWS_PUBLISHER="NEWS_PUBLISHER";
    //public static final String CONSTITUENCY="CONSTITUENCY";


    /*COMPLAINTS FILTERS- REQUEST CODES*/
    public static final int AVAILABLE_TO_BID= 1;
    public static final int ASSIGNED=2;
    public static final int COMPLETED=3;

    public static final int BIDDED=4;
    public static final int NON_BIDDED=5;


    public static final String getDate(int d, int m, int y)
    {
        String date="";
        String day=d<10?"0"+d:""+d;
        String month=m<10?"0"+m:""+m;
        String year=""+y;

        date=day+"/"+month+"/"+year;

        return date;
    }



}
