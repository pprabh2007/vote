package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import util.User;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    private Button login_button;
    private EditText username_edittext;
    Databasehelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DBHelper=new Databasehelper(this);
        DBHelper.getWritableDatabase();

        DBHelper.addUser(new User());
        User u2=new User();
        u2.setConstituency("ANC");
        DBHelper.addUser(u2);
        DBHelper.close();

        login_button=(Button)findViewById(R.id.login);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username_edittext.getText().toString().equals("rep@123.com"))
                {
                    Intent intent =new Intent(MainActivity.this, RepresentativePortal.class);
                    startActivity(intent);
                }
                else {
                Intent intent =new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);}
            }
        });

        username_edittext=(EditText)findViewById(R.id.input_username);



    }

    public void launchRegistration(View view)
    {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);

    }
}