package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import util.User;

public class MyProfile extends AppCompatActivity {


    Databasehelper DBHelper;
    Bundle extras;
    User THIS_USER_OBJECT;

    private TextView name,dob,voterid,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        DBHelper = new Databasehelper(this);
        extras=getIntent().getExtras();
        THIS_USER_OBJECT=(User)extras.getSerializable("THIS_USER_OBJECT");

        name=(TextView)findViewById(R.id.my_profile_name_edit) ;
        dob=(TextView)findViewById(R.id.my_profile_dob_edit);
        voterid=(TextView)findViewById(R.id.my_profile_voterid_edit);
        username=(TextView)findViewById(R.id.my_profile_username_edit);

        name.setText(THIS_USER_OBJECT.getName());
        dob.setText(THIS_USER_OBJECT.getDate());
        voterid.setText(THIS_USER_OBJECT.getVoterID());
        username.setText(THIS_USER_OBJECT.getUser_name());
    }
}
