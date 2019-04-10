package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import util.Complaint;

public class TrackComplaintActivity extends AppCompatActivity {


    private Databasehelper DBHelper;
    private String issue;
    private Button track;
    private EditText issue_no;
    private TextView error_message,track_com_issue,track_com_issue_edit,track_com_title,track_com_title_edit,track_com_date,track_com_date_edit,track_com_status,track_com_status_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_complaint);
        DBHelper=new Databasehelper(this);

        error_message=findViewById(R.id.error_message);
        track_com_issue=findViewById(R.id.track_com_issue);
        track_com_issue_edit=findViewById(R.id.track_com_issue_edit);
        track_com_title=findViewById(R.id.track_com_title);
        track_com_title_edit=findViewById(R.id.track_com_title_edit);
        track_com_date=findViewById(R.id.track_com_date);
        track_com_date_edit=findViewById(R.id.track_com_date_edit);
        track_com_status=findViewById(R.id.track_com_status);
        track_com_status_edit=findViewById(R.id.track_com_status_edit);
        track=findViewById(R.id.track_com);

        issue_no=findViewById(R.id.track_com_edit);


        error_message.setVisibility(View.INVISIBLE);
        track_com_issue.setVisibility(View.INVISIBLE);
        track_com_issue_edit.setVisibility(View.INVISIBLE);
        track_com_title.setVisibility(View.INVISIBLE);
        track_com_title_edit.setVisibility(View.INVISIBLE);
        track_com_date.setVisibility(View.INVISIBLE);
        track_com_date_edit.setVisibility(View.INVISIBLE);
        track_com_status.setVisibility(View.INVISIBLE);
        track_com_status_edit.setVisibility(View.INVISIBLE);

        //issue is the issue ID

        issue_no.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                issue="CM";
                issue+=issue_no.getText().toString();
            }
        });



        track.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                Complaint retrieved = DBHelper.getComplaint(issue);
                if (retrieved != null) {
                    error_message.setVisibility(View.INVISIBLE);
                    //sample CODE
                    //only if complaint found

                    track_com_issue_edit.setText(retrieved.getID());
                    track_com_title_edit.setText(retrieved.getTitle());
                    track_com_date_edit.setText(retrieved.getDate());
                    track_com_status_edit.setText(retrieved.getStatus_description());


                    error_message.setVisibility(View.INVISIBLE);

                    track_com_issue.setVisibility(View.VISIBLE);
                    track_com_issue_edit.setVisibility(View.VISIBLE);
                    track_com_title.setVisibility(View.VISIBLE);
                    track_com_title_edit.setVisibility(View.VISIBLE);
                    track_com_date.setVisibility(View.VISIBLE);
                    track_com_date_edit.setVisibility(View.VISIBLE);
                    track_com_status.setVisibility(View.VISIBLE);
                    track_com_status_edit.setVisibility(View.VISIBLE);


                } else {
                    error_message.setVisibility(View.VISIBLE);

                    track_com_issue.setVisibility(View.INVISIBLE);
                    track_com_issue_edit.setVisibility(View.INVISIBLE);
                    track_com_title.setVisibility(View.INVISIBLE);
                    track_com_title_edit.setVisibility(View.INVISIBLE);
                    track_com_date.setVisibility(View.INVISIBLE);
                    track_com_date_edit.setVisibility(View.INVISIBLE);
                    track_com_status.setVisibility(View.INVISIBLE);
                    track_com_status_edit.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
}
