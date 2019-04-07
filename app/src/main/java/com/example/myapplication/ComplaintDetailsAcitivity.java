package com.example.myapplication;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ComplaintDetailsAcitivity extends AppCompatActivity {

    ImageView DetCom_close;
    TextView DetCom_title, DetCom_date, DetCom_id, DetCom_description, DetCom_status, DetCom_upvotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_details_acitivity);

        DetCom_close=(ImageView)findViewById(R.id.DetCom_close);
        DetCom_title=(TextView)findViewById(R.id.DetCom_title);
        DetCom_date=(TextView)findViewById(R.id.DetCom_date);
        DetCom_status=(TextView)findViewById(R.id.DetCom_status);
        DetCom_upvotes=(TextView)findViewById(R.id.DetCom_upvotes);
        DetCom_description=(TextView)findViewById(R.id.DetCom_description);
        DetCom_id=(TextView)findViewById(R.id.DetCom_id);

        Bundle extras=getIntent().getExtras();
        DetCom_title.setText(extras.getString("title"));
        DetCom_description.setText(extras.getString("description"));
        DetCom_date.setText("Lodged On:  "+extras.getString("date"));
        DetCom_id.setText("Issue ID:  "+extras.getString("id"));
        DetCom_status.setText("Current Status:  "+extras.getString("status"));
        DetCom_upvotes.setText("Upvotes:  "+extras.getInt("upvotes")+"");

        DetCom_close.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                finish();
                                            }
                                        }
        );


    }
}
